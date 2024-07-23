package com.itwill.springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot3.domain.Job;

/*
 * Repository<T, ID>
 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 *     |__ JpaRepository<T, ID>
 *     		|__ EmployeeRepository<T, ID>
 *     			|__ SimpleJpaRepository<T, ID>
 * 
 * T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입
 */

public interface JobRepository extends JpaRepository<Job, String>{
	
}
