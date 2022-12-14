package com.pnu.logdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("LogDAOH2")
public class LogDAOH2 implements LogDAO{
	
	private Connection con = null;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public LogDAOH2() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mvcboard", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addlog(String method, String sql, boolean success) {
		String sqlString = "insert into dblog(method, sqlstring, success) values(?,?,?)";
//		String sqls = String.format("insert into dblog(method, sqlstring, success) values('%s', '%s', '%s')",
//				method, sql, success);
		try {
//			jdbcTemplate.update(sqls);
			jdbcTemplate.update(sqlString, method, sql, success);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
