package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // -> @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 초기화할 수 있는 아규먼트들을 갖는 생성자
@Builder 
public class UserDto { // input태그의 name 속성 값을 필드로 적음.
	private String username;
	private Integer age; // Integer의 기본값은 null
	// int의 기본값은 0이라 "나이 입력" 칸에 아무것도 안 적으면 발생하는 에러를 방지하기 위해 Integer 사용.
	
}
