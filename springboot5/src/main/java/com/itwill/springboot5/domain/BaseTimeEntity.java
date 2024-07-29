package com.itwill.springboot5.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
// javax.persistence.MappedSuperclass => 옛날 버전이라서 21버전에서는 컴파일 안 됨.
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode // equals(), hashCode(), canEqual()
@MappedSuperclass // 다른 엔터티 클래스의 상위 클래스로 사용됨.
@EntityListeners(AuditingEntityListener.class) // 엔터티 (최초) 생성 시간, (최종) 수정 시간 등을 자동으로 DB에 저장하기 위해 사용됨.
public class BaseTimeEntity {
	@CreatedDate // 엔터티 (최초) 생성 시간을 저장하는 필드.
	private LocalDateTime createdTime;
	
	@LastModifiedDate // 엔터티 (최종) 수정 시간을 저장하는 필드.
	private LocalDateTime modifiedTime;
}
