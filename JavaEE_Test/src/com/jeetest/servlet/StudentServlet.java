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

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StudentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void modifyInf(HttpServletRequest request, HttpServletResponse response) 
    		throws NamingException, IOException,ServletException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
		HttpSession hs=request.getSession();
		PrintWriter out=response.getWriter();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String strDate=sdf.format(date);
		try {
    		conn=DBUtil.getConnection();
    		String sql="update student set gender=? , age=? , birthday=? , academy=? ,classes=? , emial=? , phone=? where sid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1, request.getParameter("gender")=="" ? "***" :request.getParameter("gender"));
    		stmt.setInt(2, request.getParameter("age")=="" ? 0:Integer.parseInt(request.getParameter("age")));
    		stmt.setString(3, request.getParameter("birthday")=="" ? strDate : request.getParameter("birthday"));
    		stmt.setString(4, request.getParameter("acad")=="" ? "***" :request.getParameter("acad"));
    		stmt.setString(5, request.getParameter("classes")=="" ? "***" :request.getParameter("classes"));
    		stmt.setString(6, request.getParameter("email")=="" ? "***" :request.getParameter("email"));
    		stmt.setString(7, request.getParameter("phone")=="" ? "***" :request.getParameter("phone"));
    		stmt.setString(8, (String)hs.getAttribute("idk"));
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
        			
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">修改密码失败！</p><br>");
        		}
    		}
    		else {
    			out.print("<p style=\"font-size:24px\">原密码输入错误!...</p><br>");
    			response.setHeader("refresh", "1;url='/JavaEE_Test/student/modifySPsd.jsp'");
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
    public void chooseCourse(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, NamingException, IOException,ServletException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt2 = null;
    	ResultSet rs = null;
		HttpSession hs=request.getSession();
		PrintWriter out=response.getWriter();
		int f=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql="insert into choosecourse(sid,cid) values(?,?)";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,(String)hs.getAttribute("idk"));
    		stmt.setString(2,request.getParameter("cid"));
    		f=stmt.executeUpdate();
    		if(f>=1) {
    			sql="select cname from course where cid=?";
    			stmt2=conn.prepareStatement(sql);
    			stmt2.setString(1,request.getParameter("cid"));
    			rs=stmt2.executeQuery();
    			out.print("<p style=\"font-size:20px\">选课成功...</p>");
    			if(rs.next())out.print("已选择课程："+rs.getString(1));
    		}
    		else {
    			out.print("<p style=\"font-size:20px\">选课失败...</p><br>");
    		}
    		response.setHeader("refresh", "2;url='/JavaEE_Test/student/chooseCourse.jsp'");
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
    public void dropCourse(HttpServletRequest request, HttpServletResponse response)
    		throws NamingException, IOException,ServletException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
		HttpSession hs=request.getSession();
		PrintWriter out=response.getWriter();
		int f=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql="delete from choosecourse where sid=? and cid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,(String)hs.getAttribute("idk"));
    		stmt.setString(2,request.getParameter("cid"));
    		f=stmt.executeUpdate();
    		if(f>=1) {
    			out.print("<p style=\"font-size:20px\">退选成功...</p><br>");
    		}
    		else {
    			out.print("<p style=\"font-size:20px\">退选失败...</p><br>");
    		}
    		response.setHeader("refresh", "1;url='/JavaEE_Test/student/dropCourse.jsp'");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String oper=request.getParameter("oper");
		if(oper==null)oper="oper";
		PrintWriter out=response.getWriter();
		switch(oper) {
		case "baseInf":
			response.sendRedirect("/JavaEE_Test/student/studentBaseInf.jsp");
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
		case "chooseCourse":
			try {
				chooseCourse(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			break;
		case "resultCourse":
			response.sendRedirect("/JavaEE_Test/student/resultCourse.jsp");
			break;
		case "dropCourse":
			try {
				dropCourse(request, response);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			break;
		case "checkGrade":
			response.sendRedirect("/JavaEE_Test/student/checkGrade.jsp");
			break;
		default:
			out.print("操作错误！..."+"<br>");	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
