package com.itwill.springboot2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	
}
