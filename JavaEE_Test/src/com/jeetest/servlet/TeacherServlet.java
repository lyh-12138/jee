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
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
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
    		String sql="update teacher \r\n" + 
    				"set gender=? , age=? , academy=? ,title=? , hiredate=? , emial= ? , phone=? \r\n" + 
    				"where tid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1, request.getParameter("gender")=="" ? "***" : request.getParameter("gender"));
    		stmt.setInt(2, request.getParameter("age")=="" ? 0:Integer.parseInt(request.getParameter("age")));
    		stmt.setString(3, request.getParameter("acad")=="" ? "***" :request.getParameter("acad"));
    		stmt.setString(4, request.getParameter("title")=="" ? "***" :request.getParameter("title"));
    		stmt.setString(5, request.getParameter("hiredate")=="" ? strDate : request.getParameter("hiredate"));
    		stmt.setString(6, request.getParameter("emial")=="" ? "***" : request.getParameter("emial"));
    		stmt.setString(7, request.getParameter("phone")=="" ? "***" : request.getParameter("phone"));
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
        			response.setHeader("refresh", "1;url=/JavaEE_Test/main/right.jsp");
        		}
        		else {
        			out.print("<p style=\"font-size:24px\">修改密码失败！</p><br>");
        			response.setHeader("refresh", "1;url=/JavaEE_Test/teacher/modifyTPsd.jsp");
        		}
    		}
    		else {
    			out.print("<p style=\"font-size:24px\">原密码输入错误!...</p><br>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/teacher/modifyTPsd.jsp");
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

    public void enterScore(HttpServletRequest request, HttpServletResponse response)
    		throws NamingException,ServletException, IOException{
    	Connection conn=null;
    	PreparedStatement stmt = null;
    	PreparedStatement stmt1 = null;
		ResultSet rs = null;
		PrintWriter out=response.getWriter();
		String cid=request.getParameter("cid");
		int count=0;
		try {
    		conn=DBUtil.getConnection();
    		String sql = "select student.sid,student.sname, grade\r\n"
					+"from student , choosecourse\r\n" 
					+"where student.sid=choosecourse.sid and grade is null and cid=?";
			stmt=conn.prepareStatement(sql);
  			stmt.setString(1,cid);
    		rs=stmt.executeQuery();
    		while(rs.next()) {
    			String sgrade=request.getParameter(rs.getString(1));
    			if(sgrade.equals("")) {
    				continue;
    			}
    			double grade=Double.parseDouble(sgrade);
    			sql="update choosecourse set grade=? where sid=? and cid=?";
    			stmt1=conn.prepareStatement(sql);
    			stmt1.setDouble(1, grade);
    			stmt1.setString(2, rs.getString(1));
    			stmt1.setString(3, cid);
    			if(stmt1.executeUpdate()>0)count++;
    		}
    		out.print("<h3>录入成绩成功！</h3><br><p>已成功录入成绩"+count+"条<br></p>");
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String oper=request.getParameter("oper");
		if(oper==null)oper="oper";
		PrintWriter out=response.getWriter();
		switch(oper) {
		case "baseInf":
			response.sendRedirect("/JavaEE_Test/teacher/teacherBaseInf.jsp");
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
		case "checkSGrade":
			response.sendRedirect("/JavaEE_Test/teacher/showSGrade.jsp?sid="+request.getParameter("sid"));
			break;
		case "checkCGrade":
			out.print(request.getParameter("carlist"));
			response.sendRedirect("/JavaEE_Test/teacher/showCGrade.jsp?pageNumber=1&cid="+request.getParameter("carlist"));
			break;
		case "enterScore":
			try {
				enterScore(request,response);
			} catch (NamingException e) {
				e.printStackTrace();
			}
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
