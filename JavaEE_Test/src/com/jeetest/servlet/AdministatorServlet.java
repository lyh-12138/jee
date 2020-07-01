package com.jeetest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jeetest.util.*;

/**
 * Servlet implementation class AdministatorServlet
 */
@WebServlet("/AdministatorServlet")
public class AdministatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws NamingException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void register(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException,IOException, NamingException {
    	PrintWriter out=response.getWriter();
    	Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	ResultSet rs = null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select  * from verification where idk=?";
    		stmt1=conn.prepareStatement(sql);
    		stmt1.setString(1, request.getParameter("aid"));
    		rs=stmt1.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:20px\">注册失败,该用户已存在！</p><br>");
    			response.setHeader("refresh", "1;url='/JavaEE_Test/register.jsp'");
    		}
    		else {
    			sql="insert into verification values(?,?,?,1)";
        		stmt=conn.prepareStatement(sql);
        		stmt.setString(1, request.getParameter("aid"));
        		stmt.setString(2, request.getParameter("username"));
        		stmt.setString(3, request.getParameter("psdf"));
        		int f=stmt.executeUpdate();
        		if(f>=1) {
        			out.print("<p style=\"font-size:20px\">注册成功!</p><br>");
        			response.setHeader("refresh", "1;url='/JavaEE_Test/login1.jsp'");
        		}
        		else {
        			out.print("<p style=\"font-size:20px\">注册失败!</p><br>");
        			response.setHeader("refresh", "1;url='/JavaEE_Test/register.jsp'");
        		}
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
    }
    public void modifyInf(HttpServletRequest request, HttpServletResponse response) 
    		throws NamingException, IOException,ServletException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
		HttpSession hs=request.getSession();
		PrintWriter out=response.getWriter();
		try {
    		conn=DBUtil.getConnection();
    		String sql="update administator \r\n" + 
    				"set age=? , gender=? , birthday=?, emial= ? , phone=? \r\n" + 
    				"where aid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setInt(1, request.getParameter("age")=="" ? 0:Integer.parseInt(request.getParameter("age")));
    		stmt.setString(2, request.getParameter("gender")=="" ? "***" :request.getParameter("gender"));
    		stmt.setString(3, request.getParameter("birthday")=="" ? "1900-01-01" :request.getParameter("birthday"));
    		stmt.setString(4, request.getParameter("emial")=="" ? "***" :request.getParameter("emial"));
    		stmt.setString(5, request.getParameter("phone")=="" ? "***" : request.getParameter("phone"));
    		stmt.setString(6, (String)hs.getAttribute("idk"));
    		int f=stmt.executeUpdate();
    		if(f>=1) {
    			out.print("<p style=\"font-size:24px\">修改信息成功...</p><br>");
    		}
    		else {
    			out.print("<p style=\"font-size:24px\">修改信息失败...</p><br>");
    		}
		}
    	catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
    }
    public void modifyPsd(HttpServletRequest request, HttpServletResponse response) 
    		throws NamingException, IOException,ServletException, ParseException{
    	Connection conn=null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
		ResultSet rs = null;
		HttpSession hs=request.getSession();
		PrintWriter out=response.getWriter();
		int f=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql="select * from verification where idk=? and passwords=?";
    		stmt1=conn.prepareStatement(sql);
    		stmt1.setString(1,(String)hs.getAttribute("idk"));
    		stmt1.setString(2,request.getParameter("lpsd"));
    		rs=stmt1.executeQuery();
    		if(rs.next()) {
    			sql="update verification set passwords=? where idk=?";
    			stmt2=conn.prepareStatement(sql);
    			stmt2.setString(1, request.getParameter("npsd"));
    			stmt2.setString(2, (String)hs.getAttribute("idk"));
    			f=stmt2.executeUpdate();
    			if(f>=1) {
        			out.print("<p style=\"font-size:24px\">修改密码成功！</p><br>");
        			response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">修改密码失败！</p><br>");
        			response.setHeader("refresh", "1;url=/JavaEE_Test/teacher/modifyAPsd.jsp");
        		}
    		}
    		else {
    			out.print("<p style=\"font-size:24px\">原密码输入错误!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/modifyAPsd.jsp");
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
    }
    public void addCourse(HttpServletRequest request, HttpServletResponse response) 
    		throws NamingException, IOException,ServletException, ParseException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PrintWriter out=response.getWriter();
		int f=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql1="select * from course where cid=?";
    		String sql2="select * from teacher where tid=?";
    		stmt=conn.prepareStatement(sql1);
    		stmt.setString(1,request.getParameter("cid"));
    		rs1=stmt.executeQuery();
    		stmt1=conn.prepareStatement(sql2);
    		stmt1.setString(1, request.getParameter("tid"));
    		rs2=stmt1.executeQuery();
    		if(rs1.next()) {
    			out.print("<p style=\"font-size:24px\">该课程已经存在!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addCourse.jsp");
    		}
    		else if(!rs2.next()) {
    			out.print("<p style=\"font-size:24px\">输入的教师不存在!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addCourse.jsp");
    		}
    		else {
    			String sql="insert into course(cid,cname,semester,credit,classHour,tid) values \r\n"
    					+"(?,?,?,?,?,?)";
    			stmt2=conn.prepareStatement(sql);
    			stmt2.setString(1, request.getParameter("cid"));
    			stmt2.setString(2, request.getParameter("cname"));
    			stmt2.setString(3, request.getParameter("semester")=="" ? "0000-0000-0":request.getParameter("semester"));
    			stmt2.setInt(4,request.getParameter("credit")==""? 0:Integer.parseInt(request.getParameter("credit")));
    			stmt2.setInt(5,request.getParameter("classhour")==""?0:Integer.parseInt(request.getParameter("classhour")));
    			stmt2.setString(6, request.getParameter("tid"));
    			f=stmt2.executeUpdate();
    			if(f>0) {
    				out.print("<p style=\"font-size:24px\">添加课程成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
    			}
    			else {
    				out.print("<p style=\"font-size:24px\">添加课程失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addCourse.jsp");
    			}
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
    		if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
    }
	public void dropCourse(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt2=null;
    	ResultSet rs=null;
    	PrintWriter out=response.getWriter();
    	int f=0;
		try {
    		conn=DBUtil.getConnection();
    		/*String sql="SET FOREIGN_KEY_CHECKS=0; \r\n "
    				+ "delete from course where cid=?; \r\n"
    				+ "SET FOREIGN_KEY_CHECKS=1;";*/
    		String sql="select * from choosecourse where cid =?";
    		stmt2=conn.prepareStatement(sql);
    		stmt2.setString(1,request.getParameter("cid"));
    		rs=stmt2.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:24px\">该课程已经被发布并被学生选择，无法进行删除!</p>");
				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/courseResearch.jsp?oper=dropCourse");
    		}
    		else {
    			String sql1="delete from course where cid=?";
        		stmt=conn.prepareStatement(sql1);
        		stmt.setString(1,request.getParameter("cid"));
        		f=stmt.executeUpdate();
        		if(f>0) {
        			out.print("<p style=\"font-size:24px\">删除课程成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">删除课程失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/courseResearch.jsp?oper=dropCourse");
        		}
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	public void modifyCourse(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	ResultSet rs=null;
		PrintWriter out=response.getWriter();
		try {
    		conn=DBUtil.getConnection();
    		String sql="select * from teacher where tid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1, request.getParameter("tid"));
    		rs=stmt.executeQuery();
    		if(!rs.next()) {
    			out.print("<p style=\"font-size:24px\">你所输入的任课教师不存在!</p>");
				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/modifyCourse.jsp?cid="+request.getParameter("cid"));
    		}
    		else {
    			sql="update course set cname=? , semester=?, credit= ? , classHour=? ,tid=? where cid=?";
        		stmt1=conn.prepareStatement(sql);
        		stmt1.setString(1, request.getParameter("cname"));
        		stmt1.setString(2, request.getParameter("semester")=="" ? "0000-0000-1":request.getParameter("semester"));
        		stmt1.setInt(3, request.getParameter("credit")==""? 0:Integer.parseInt(request.getParameter("credit")));
        		stmt1.setInt(4, request.getParameter("classHour")=="" ? 0:Integer.parseInt(request.getParameter("classHour")));
        		stmt1.setString(5, request.getParameter("tid"));
        		stmt1.setString(6, request.getParameter("cid"));
        		int f=stmt1.executeUpdate();
        		if(f>=1) {
        			out.print("<p style=\"font-size:24px\">课程修改成功...</p><br>");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">课程修改失败...</p><br>");
        		}
    		}	
		}
    	catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	public void addTeacher(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
		ResultSet rs = null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String strDate=sdf.format(date);
		PrintWriter out=response.getWriter();
		int f=0;
		int v=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql1="select * from teacher where tid=?";
    		stmt=conn.prepareStatement(sql1);
    		stmt.setString(1,request.getParameter("tid"));
    		rs=stmt.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:24px\">该教师已经存在!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addTeacher.jsp");
    		}
    		else {
    			
    			String sql2="insert into verification values (?,?,'111111',2)";
    			stmt2=conn.prepareStatement(sql2);
    			stmt2.setString(1, request.getParameter("tid"));
    			stmt2.setString(2, request.getParameter("tname"));
    			v=stmt2.executeUpdate();
    			String sql="insert into teacher values (?,?,?,?,?,?,?,?,?)";
    			stmt1=conn.prepareStatement(sql);
    			stmt1.setString(1, request.getParameter("tid"));
    			stmt1.setString(2, request.getParameter("tname"));
    			stmt1.setInt(3, request.getParameter("age")==""? 0:Integer.parseInt(request.getParameter("age")));
    			stmt1.setString(4,request.getParameter("gender")=="" ? "***":request.getParameter("gender"));
    			stmt1.setString(5,request.getParameter("acad")=="" ? "***":request.getParameter("academy"));
    			stmt1.setString(6, request.getParameter("title")=="" ? "***":request.getParameter("title"));
    			stmt1.setString(7,request.getParameter("hiredate")=="" ? strDate:request.getParameter("hiredate"));
    			stmt1.setString(8,request.getParameter("emial")=="" ? "***":request.getParameter("emial"));
    			stmt1.setString(9,request.getParameter("phone")=="" ? "***":request.getParameter("phone"));
    			f=stmt1.executeUpdate();
    			if(f>0 && v>0) {
    				out.print("<p style=\"font-size:24px\">添加教师成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
    			}
    			else {
    				out.print("<p style=\"font-size:24px\">添加教师失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addTeacher.jsp");
    			}
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
    		if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	public void dropTeacher(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
    	ResultSet rs = null;
    	PrintWriter out=response.getWriter();
    	int v=0;
    	int f=0;
		try {
    		conn=DBUtil.getConnection();
    		/*String sql="SET FOREIGN_KEY_CHECKS=0; \r\n "
    				+ "delete from teacher where tid=?; \r\n"
    				+ "SET FOREIGN_KEY_CHECKS=1;";*/
    		String sql="select * from course where tid =?";
    		stmt2=conn.prepareStatement(sql);
    		stmt2.setString(1,request.getParameter("tid"));
    		rs=stmt2.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:24px\">该教师已经与某门开设的课程关联，无法进行删除!</p>");
				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/teacherInput.jsp");
    		}
    		else {
    			String sql1="delete from verification where idk=?";
    			stmt1=conn.prepareStatement(sql1);
        		stmt1.setString(1,request.getParameter("tid"));
        		v=stmt1.executeUpdate();
        		
    			String sql2="delete from teacher where tid=?";
    			stmt=conn.prepareStatement(sql2);
        		stmt.setString(1,request.getParameter("tid"));
        		f=stmt.executeUpdate();
        		if(f>0 && v>0) {
        			out.print("<p style=\"font-size:24px\">删除教师成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">删除教师失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/teacherInput.jsp");
        		}
    		}
    		
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	public void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
		ResultSet rs = null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String strDate=sdf.format(date);
		PrintWriter out=response.getWriter();
		int f=0;
		int v=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql1="select * from student where sid=?";
    		stmt=conn.prepareStatement(sql1);
    		stmt.setString(1,request.getParameter("sid"));
    		rs=stmt.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:24px\">该学生已经存在!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addStudent.jsp");
    		}
    		else {
    			String sql2="insert into verification values (?,?,'111111',3)";
    			stmt2=conn.prepareStatement(sql2);
    			stmt2.setString(1, request.getParameter("sid"));
    			stmt2.setString(2, request.getParameter("sname"));
    			v =stmt2.executeUpdate();
    			
    			String sql="insert into student(sid,sname,age,gender,birthday,academy,classes,emial,phone) values \r\n"
    					+"(?,?,?,?,?,?,?,?,?)";
    			stmt1=conn.prepareStatement(sql);
    			stmt1.setString(1, request.getParameter("sid"));
    			stmt1.setString(2, request.getParameter("sname"));
    			stmt1.setInt(3, request.getParameter("age")==""? 0:Integer.parseInt(request.getParameter("age")));
    			stmt1.setString(4,request.getParameter("gender")=="" ? "***":request.getParameter("gender"));
    			stmt1.setString(5,request.getParameter("birthday")=="" ? strDate:request.getParameter("birthday"));
    			stmt1.setString(6, request.getParameter("acad")=="" ? "***":request.getParameter("acad"));
    			stmt1.setString(7,request.getParameter("classes")=="" ? "***":request.getParameter("classes"));
    			stmt1.setString(8,request.getParameter("emial")=="" ? "***":request.getParameter("emial"));
    			stmt1.setString(9,request.getParameter("phone")=="" ? "***":request.getParameter("phone"));
    			f=stmt1.executeUpdate();
    			if(f>0 && v>0) {
    				out.print("<p style=\"font-size:24px\">添加学生成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
    			}
    			else {
    				out.print("<p style=\"font-size:24px\">添加学生失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/addStudent.jsp");
    			}
    		}
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
    		if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	public void dropStudent(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, IOException,ServletException, ParseException{
		Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
    	ResultSet rs = null;
    	PrintWriter out=response.getWriter();
    	int v=0;
    	int f=0;
		try {
    		conn=DBUtil.getConnection();
    		/*String sql="SET FOREIGN_KEY_CHECKS=0; \r\n "
    				+ "delete from student where sid=? ; \r\n"
    				+ "SET FOREIGN_KEY_CHECKS=1;";*/
    		String sql="select * from choosecourse where sid =?";
    		stmt2=conn.prepareStatement(sql);
    		stmt2.setString(1,request.getParameter("sid"));
    		rs=stmt2.executeQuery();
    		if(rs.next()) {
    			out.print("<p style=\"font-size:24px\">该学生已经选择了某些课程，属于在读状态，无法进行删除!</p>");
				response.setHeader("refresh", "2;url=/JavaEE_Test/administator/studentInput.jsp");
    		}
    		else {
    			String sql1="delete from verification where idk=?";
    			stmt1=conn.prepareStatement(sql1);
        		stmt1.setString(1,request.getParameter("sid"));
        		v=stmt1.executeUpdate();
        		
        		String sql2="delete from student where sid=?";
    			stmt=conn.prepareStatement(sql2);
        		stmt.setString(1,request.getParameter("sid"));
        		f=stmt.executeUpdate();
        		if(f>0 && v>0) {
        			out.print("<p style=\"font-size:24px\">删除学生成功!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">删除学生失败!</p>");
    				response.setHeader("refresh", "1;url=/JavaEE_Test/administator/studentInput.jsp");
        		}
    		}
    		
		}catch(SQLException e){
    		e.getMessage();
    	}
    	finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if(conn!=null) {
    			try {
    				conn.close();
    			} catch (SQLException e) {
    				e.getMessage();
    			}
    		}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String oper=request.getParameter("oper");
		if(oper==null)oper="oper";
		PrintWriter out=response.getWriter();
		switch(oper) {
		case "register":
			try {
				register(request,response);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			break;
		case "baseInf":
			response.sendRedirect("/JavaEE_Test/administator/administatorBaseInf.jsp");
			break;
		case "modifyInf":
			try {
				modifyInf(request,response);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			break;
		case "modifyPsd":
			try {
				modifyPsd(request,response);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case "addCourse":
			try {
				addCourse(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "dropCourse":
			try {
				dropCourse(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "modifyCourse":
			try {
				modifyCourse(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "checkCourse":
			response.sendRedirect("/JavaEE_Test/administator/showCourse.jsp?pageNumber=1");
			break;
		case "addStudent":
			try {
				addStudent(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "dropStudent":
			try {
				dropStudent(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "checkStudent":
			response.sendRedirect("/JavaEE_Test/administator/showStudent.jsp?pageNumber=1");
			break;
		case "addTeacher":
			try {
				addTeacher(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "dropTeacher":
			try {
				dropTeacher(request,response);
			} catch (NamingException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "checkTeacher":
			response.sendRedirect("/JavaEE_Test/administator/showTeacher.jsp?pageNumber=1");
			break;
		default:
			out.print("操作错误！..."+"<br>");	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
