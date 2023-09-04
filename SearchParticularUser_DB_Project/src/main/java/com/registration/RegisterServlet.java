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

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    response.setContentType("text/html");
    
    int rollNo =Integer.parseInt( request.getParameter("roll"));
    
    String studName = request.getParameter("name");
    
    String studMark = request.getParameter("mark");
    
    PrintWriter out = response.getWriter();
    
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SERVLET","root","root123");
    
    	PreparedStatement pstmt = con.prepareStatement("INSERT INTO Student VALUES(?,?,?)");
    	
    	pstmt.setInt(1, rollNo);
    	
    	pstmt.setString(2, studName);
    	
    	pstmt.setString(3, studMark);
    	
    	int row = pstmt.executeUpdate();
    	
    	if(row>0)
    	{
    		out.println("<h2>Your data are registered into the database</h2>");
    	}
    	
    	
    	
    }
    catch(Exception e )
    {
    	e.printStackTrace();
    }
		
		
		
	}
	
	
}
