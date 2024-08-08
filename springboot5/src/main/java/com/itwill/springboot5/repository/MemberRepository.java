package com.itwill.springboot5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot5.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	// select m.*, r.* from members m left join member_roles r on m.id = r.member_id where m.username = ?
	// fetch가 Lazy 타입으로 설정된 필드("roles")를 join해서 즉시(EAGER) 로딩함.
	@EntityGraph(attributePaths = "roles") // 멤버 엔터티의 필드 이름.
	Optional<Member> findByUsername(String username);
}
