package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

// DAO(Data Access Object). 데이터베이스 CRUD.
public enum UserDao {
	INSTANCE;

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

	// select() 메서드에서 실행할 SQL:
	private static final String SQL_SELECT_ALL = "select * from users order by id desc";

	public List<User> select() {
		log.debug("select()");
		log.debug(SQL_SELECT_ALL);

		List<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();

			while (rs.next()) {
				User user = fromResultSetToUser(rs);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return list;
	}

	// users 테이블에 insert하는 SQL:
	private static final String SQL_INSERT = "insert into users (userid, password, email) values (?, ?, ?)";

	public int insert(User user) {
		log.debug("insert({})", user);
		log.debug(SQL_INSERT);

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());

			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
			;
		}

		return result;
	}

	private User fromResultSetToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String userid = rs.getString("userid");
		String password = rs.getString("password");
		String email = rs.getString("email");
		int points = rs.getInt("points");

		return User.builder()
				.id(id)
				.userid(userid)
				.password(password)
				.email(email)
				.points(points)
				.build();

	}

	private void closeResources(Connection conn, Statement stmt, ResultSet rs) { // executeQuery()
		// DB 자원들을 해제하는 순서: 생성된 순서의 반대로. rs -> stmt -> conn
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeResources(Connection conn, Statement stmt) { // executeUpdate()
		closeResources(conn, stmt, null);
	}

}
