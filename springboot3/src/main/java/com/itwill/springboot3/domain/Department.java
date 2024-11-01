package com.itwill.springboot3.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "DEPARTMENTS")
public class Department {
	@Id
	@Column(name = "DEPARTMENT_ID")
	private Integer id;
	
	private String departmentName; // 컬럼 이름: department_name
	
//	private Integer managerId; // 컬럼 이름: manager_id
	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
//	private Integer locationId;
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID") // DEPARTMENTS 테이블에서 LOCATIONS 테이블과 join하는 컬럼 이름.
	private Location location;
	
	
//	@ToString.Exclude
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
//	private List<Employee> employees;
	
}
