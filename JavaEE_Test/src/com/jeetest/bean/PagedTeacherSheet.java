package com.jeetest.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jeetest.dao.*;
import com.jeetest.util.*;
public class PagedTeacherSheet {
	public List<Teacher> getTeacherList(int start, int length) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from teacher limit ?,?";
		List<Teacher> result = new ArrayList<Teacher>();
		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, length);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTid(rs.getString(1));
				teacher.setTname(rs.getString(2));
				teacher.setAge(rs.getInt(3));
				teacher.setGender(rs.getString(4));
				teacher.setAcad(rs.getString(5));
				teacher.setTitle(rs.getString(6));
				teacher.setHiredate(rs.getDate(7));
				teacher.setEmial(rs.getString(8));
				teacher.setPhone(rs.getString(9));
				result.add(teacher);
			}
			System.out.println(result.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
			}
		}
		System.out.println(result.size());
		return result;
	}

	public int totalCount() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			String sql = "select count(*) from teacher";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 执行查询
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}
}
