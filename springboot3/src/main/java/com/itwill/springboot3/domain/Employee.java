package com.itwill.springboot3.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter @ToString @EqualsAndHashCode
@Entity
@Table(name = "EMPLOYEES") // EMPLOYEES 테이블에 매핑되는 엔터티.
public class Employee {	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private Integer id;
	
	// JPA는 camel 표기법의 엔터티 필드 이름을 snake 표기법의 컬럼 이름으로 자동 매핑.
	// 필드: firstName <---> 컬럼 이름: first_name(FIRST_NAME)
	private String firstName;
	
	private String lastName; // 컬럼 이름: last_name
	
	private String email;
	
	private String phoneNumber; // 컬럼 이름: phone_number
	
	private LocalDate hireDate; // 컬럼 이름: hire_date
	
//	private String jobId; // 컬럼 이름: job_id
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_ID") // EMPLOYEES 테이블에서 JOBS 테이블과 join하는 컬럼 이름.
	private Job job;
	
	private Double salary;
	
	private Double commissionPct; // 컬럼 이름: commission_pct
	
//	private Integer managerId; // 컬럼 이름: manager_id
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
//	private Integer departmentId; // 컬럼 이름: department_id
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID") // EMPLOYEES 테이블에서 DEPARTMENTS 테이블과 join하는 컬럼 이름.
	private Department department;
	
}
