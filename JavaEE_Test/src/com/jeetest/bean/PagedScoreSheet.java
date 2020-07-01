package com.jeetest.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.jeetest.dao.*;
import com.jeetest.util.*;
public class PagedScoreSheet {
	public List<ScoreSheet> getScoreList(String cid,int start, int length) {
		System.out.println(cid);
		System.out.println(start);
		System.out.println(length);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select student.sid,student.sname,student.academy,student.classes,grade\r\n" + 
						"from choosecourse,student\r\n" + 
						"where cid=? and student.sid=choosecourse.sid and grade is not null order by grade desc limit ?,?";
		List<ScoreSheet> result = new ArrayList<ScoreSheet>();
		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, length);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				ScoreSheet sc = new ScoreSheet();
				sc.setSid(rs.getString(1));
				sc.setSname(rs.getString(2));
				sc.setAcad(rs.getString(3));
				sc.setClasses(rs.getString(4));
				sc.setGrade(rs.getDouble(5));
				result.add(sc);
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

	public int totalCount(String cid) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			connection = DBUtil.getConnection(); // 获取数据库连接
			String sql = "select count(*) from choosecourse where cid=? and grade is not null";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cid);
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
