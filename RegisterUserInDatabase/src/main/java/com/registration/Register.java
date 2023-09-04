package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String country = request.getParameter("country");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SERVLET","root","root123");
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO User VALUES (?,?,?)");
			
			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			pstmt.setString(3, country);
			
			
			int row = pstmt.executeUpdate();
			
			if(row>0)
				out.println("<h2>You are successfully registered into the database</h2>");
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
