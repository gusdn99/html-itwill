package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

/*
 * Repository<T, ID>
 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 *     |__ JpaRepository<T, ID>
 * 
 * T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입
 */

//첫 번째 타입은 테이블 @Entity의 타입, 두 번째 타입은 primary key @Id의 타입
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { 

}
