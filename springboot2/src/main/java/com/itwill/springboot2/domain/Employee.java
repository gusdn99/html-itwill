package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM(Object Relation Mapping) -> JPA(Java Persistence API) -> Hibernate
@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체 (기본 생성자와, primary key(@Id) 2가지가 있어야 함.)
@Table(name = "EMP") // 클래스 이름과 실제 테이블 이름이 다를 때. 같으면 쓸 필요X
public class Employee {
	@Id // Primary Key
	@Column(name = "EMPNO") // 필드 이름과 실제 컬럼 이름이 다를 때. 같으면 쓸 필요X
	private Integer id;
	
	private String ename;
	
	private String job;
	
//	@Column(name = "MGR")
//	private Integer manager;
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MGR")
	private Employee manager;
	
	private LocalDate hiredate;
	
	@Column(name = "SAL")
	private Double salary;
	
	@Column(name = "COMM")
	private Double commission;
	
//	private Integer deptno;
	@ToString.Exclude // toString 메서드에서 출력 문자열에서 제외.
	@ManyToOne(fetch = FetchType.LAZY)
	// @ManyToOne: 여러 개의 엔터티가(deptno) 하나의 엔터티에 연결되는 관계 (여기서는 EMP 테이블이 기준)
	// fetch의 기본값은 EAGER. 여러 테이블을 한꺼번에 빠르게 검색할 때 사용.
	// EAGER: 처음부터 join 문장을 만듦.(기본값) 연관된 엔터티를 즉시 로딩.
	// LAZY: 처음부터 join 문장을 만들지 않고 필요할 때 만듦.(권장 사항) 연관된 엔터티를 실제로 사용할 때 로딩.
	// => LAZY 사용하면 @ToString.Exclude 애너테이션도 같이 사용해야 함.
	@JoinColumn(name = "DEPTNO") // EMP 테이블에서 DEPT 테이블과 join하는 컬럼 이름.
	private Department department;
	
}
