package com.itwill.springboot5.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) // 둘 다 기본값은 false
// onlyExplicitlyIncluded 속성: @EqualsAndHashCode.Include 애너테이션이 설정된 필드만 사용할 것인지 여부.
// callSuper 속성: superclass의 equals(), hashCode() 메서드를 사용할 것인지 여부. 
@Entity
@Table(name = "MEMBERS")
public class Member extends BaseTimeEntity implements UserDetails { // UserDetails 인터페이스를 구현함.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EqualsAndHashCode.Include // username 필드를 equals()와 hashCode()를 재정의할 때 사용.
	@NaturalId // unique 제약조건
	@Basic(optional = false) // not null 제약조건
	@Column(updatable = false) // update 쿼리에서 set 절에서 제외하기 위해서.
	private String username;
	
	@Basic(optional = false)
	private String password;
	
	@Basic(optional = false)
	private String email;
	
	@Builder.Default // Builder 패턴에서도 null이 아닌 HashSet<> 객체로 초기화될 수 있도록. NullPointerException 에러 방지.
	@ToString.Exclude // toString() 메서드에서 제외.
	@ElementCollection(fetch = FetchType.LAZY) // 연관 테이블(member_roles) 사용.
	// => 엔터티이름(Member)과 필드 이름(roles)으로 연관 테이블을 찾음.
	@Enumerated(EnumType.STRING) // DB 테이블에 저장될 때 상수(enum) 이름(문자열)을 사용.
	// 1명의 멤버가 2개의 권한을 가질 수도 있음. 예) USER, ADMIN 권한 둘 다 가짐.
	// @AllArgsConstructor 애너테이션이 있는데, 이게 roles 필드를 null로 만들 수도 있음.
	// 그러면 빌더 패턴 만들 때 NullPointerException 에러 뜸.
	private Set<MemberRole> roles = new HashSet<>();
	
	public Member addRole(MemberRole role) {
		// getRoles().add(role); // 메서드 안 만드는 대신 이 코드만 작성해도 됨.
		roles.add(role); // Set<>에 원소를 추가.
		return this;
	}
	
	public Member removeRole(MemberRole role) {
		roles.remove(role); // Set<>에서 원소(role)를 삭제.
		return this;
	}
	
	public Member clearRoles() {
		roles.clear(); // Set<T>의 모든 원소들을 지움.
		return this;
	}
	
	@Override
	// GrantedAuthority 객체를 만들어서 리스트에 넣어줌.
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for (MemberRole r : roles) {
//			GrantedAuthority auth = new SimpleGrantedAuthority(r.getAuthority());
//			authorities.add(auth);
//		}
		
		List<SimpleGrantedAuthority> authorities = roles.stream()
				.map((r) -> new SimpleGrantedAuthority(r.getAuthority()))
				.toList();
		
		return authorities;
	}
}
