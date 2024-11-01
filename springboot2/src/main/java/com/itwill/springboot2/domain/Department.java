package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM(Object Relation Mapping) -> JPA(Java Persistence API) -> Hibernate
@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체 (기본 생성자와, primary key(@Id) 2가지가 있어야 함.)
@Table(name = "DEPT") // 클래스 이름과 실제 테이블 이름이 다를 때.
public class Department {
	@Id // Primary Key
	@Column(name = "DEPTNO") // 필드 이름과 실제 컬럼 이름이 다를 때.
	private Integer id;
	
	private String dname;
	
	@Column(name = "LOC")
	private String location;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	// @OneToMany: 하나의 엔터티가 여러 엔터티에 연결되는 관계
	// fetch의 기본값은 EAGER. 여러 테이블을 한꺼번에 빠르게 검색할 때 사용.
	// EAGER: 처음부터 join 문장을 만듦.(기본값) 연관된 엔터티를 즉시 로딩.
	// LAZY: 처음부터 join 문장을 만들지 않고 필요할 때 만듦.(권장 사항) 연관된 엔터티를 실제로 사용할 때 로딩.
	// => LAZY 사용하면 @ToString.Exclude 애너테이션도 같이 사용해야 함.
	// mappedBy: Employee 엔터티에서 @ManyToOne 애너테이션이 설정된 필드 이름.
	private List<Employee> employees;
	
}
