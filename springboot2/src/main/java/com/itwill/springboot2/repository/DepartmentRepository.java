package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Department;

//첫 번째 타입은 테이블 @Entity의 타입, 두 번째 타입은 primary key @Id의 타입
public interface DepartmentRepository extends JpaRepository<Department, Integer> { 

}
