package com.jeetest.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	public static Connection getConnection() throws NamingException,SQLException {
		Connection result = null;
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MySQL");
		result = ds.getConnection();
		return result;
}
}
