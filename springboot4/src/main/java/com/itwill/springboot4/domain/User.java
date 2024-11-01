package com.itwill.springboot4.domain;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generated as identity
	private Long id;
	
	@NaturalId // Unique 제약조건
	@Basic(optional = false) // not null 제약조건 (기본값은 true)
	private String username;
	
	@Basic(optional = false)
	private String password;
	
	@Enumerated(EnumType.STRING)// 기본값은 ORDINAL
	// enum에서 설정한 상수 필드들을 순서대로 지정함.
	// ORDINAL -> number 타입으로 지정.(0부터 시작) enum에 있는 필드들의 인덱스 범위 안에서만(여기서는 0 ~ 2) insert 가능함. 
	// STRING -> varchar2 타입으로 지정. enum에 있는 필드들만 insert 가능함.
	private Gender gender;
	
	@Column(length = 1000) // length를 설정하지 않으면 String의 기본 길이는 varchar2(255 char)
	private String memo;
	
	@Embedded // @Embeddable로 선언된 객체를 포함.
	private Address address;
	
}
