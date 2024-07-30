package com.itwill.springboot5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot5.domain.Post;

// CRUD + Paging/Sorting
public interface PostRepository extends JpaRepository<Post, Long>{
	@Query("SELECT p FROM Post p WHERE " +
	           "(:category = 't' AND UPPER(p.title) LIKE UPPER(CONCAT('%', :keyword, '%'))) OR " +
	           "(:category = 'c' AND UPPER(p.content) LIKE UPPER(CONCAT('%', :keyword, '%'))) OR " +
	           "(:category = 'tc' AND (UPPER(p.title) LIKE UPPER(CONCAT('%', :keyword, '%')) OR UPPER(p.content) LIKE UPPER(CONCAT('%', :keyword, '%')))) OR " +
	           "(:category = 'a' AND UPPER(p.author) LIKE UPPER(CONCAT('%', :keyword, '%')))")
	    Page<Post> search(@Param("category") String category, @Param("keyword") String keyword, Pageable pageable);
}
