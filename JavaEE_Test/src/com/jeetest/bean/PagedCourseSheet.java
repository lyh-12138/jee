package com.jeetest.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jeetest.dao.*;
import com.jeetest.util.*;
public class PagedCourseSheet {
	public List<Course> getCourseList(int start, int length) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select cid ,cname,semester,credit,classhour,tname\r\n" + 
						"from course,teacher\r\n" + 
						"where course.tid=teacher.tid limit ?,?";
		List<Course> result = new ArrayList<Course>();
		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, length);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				Course course = new Course();
				course.setCid(rs.getString(1));
				course.setCname(rs.getString(2));
				course.setSemester(rs.getString(3));
				course.setCredit(rs.getInt(4));
				course.setClassHour(rs.getInt(5));
				course.setTname(rs.getString(6));
				result.add(course);
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
			String sql = "select count(*) from course";
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
