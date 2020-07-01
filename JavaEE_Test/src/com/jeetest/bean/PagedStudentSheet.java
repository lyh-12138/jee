package com.jeetest.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jeetest.dao.*;
import com.jeetest.util.*;
public class PagedStudentSheet {
	public List<Student> getStudentList(int start, int length) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from student limit ?,?";
		List<Student> result = new ArrayList<Student>();
		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, length);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				Student student = new Student();
				student.setSid(rs.getString(1));
				student.setSname(rs.getString(2));
				student.setAge(rs.getInt(3));
				student.setGender(rs.getString(4));
				student.setClasses(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setBirthday(rs.getDate(7));
				student.setAcad(rs.getString(8));
				student.setPhone(rs.getString(9));
				result.add(student);
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
			String sql = "select count(*) from student";
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
