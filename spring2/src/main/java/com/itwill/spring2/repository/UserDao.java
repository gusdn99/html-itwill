package com.itwill.spring2.repository;

public interface UserDao {
	
	User selectByUserId(String userid);
	User selectByUseridAndPassword(User user);
	int insertUser(User user);
	int updatePoints(String userid, Integer points);

}

