package com.itwill.lab05.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

//서비스(비즈니스) 계층 싱글턴 객체.
public enum UserService {
	INSTANCE;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserDao userDao = UserDao.INSTANCE;

	// 전체 목록
	public List<User> read() {
		log.debug("read()");
		List<User> list = userDao.select();
		log.debug("list size = {}", list.size());

		return list;
	}

	// TODO: 회원 가입에 필요한 메서드. userDao.insert() 호출.

	// 새 글 작성
	public int create(User user) {
		log.debug("create(user = {})", user);

		// Repository 계층의 메서드를 사용해서 DB 테이블에 행을 삽입(insert)
		int result = userDao.insert(user);
		log.debug("insert result = {}", result);

		return result; // insert된 행의 개수를 리턴.
	}

}
