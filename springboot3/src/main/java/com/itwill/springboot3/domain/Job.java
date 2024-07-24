package com.itwill.springboot3.domain;

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

@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity
@Table(name = "JOBS")
public class Job {
	
	@Id
	@Column(name = "JOB_ID")
	private String id;
	
	private String jobTitle; // 컬럼 이름: job_title
	
	private Integer minSalary; // 컬럼 이름: min_salary
	
	private Integer maxSalary; // 컬럼 이름: max_salary
	
//	@ToString.Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
//	private List<Employee> employees;
}
