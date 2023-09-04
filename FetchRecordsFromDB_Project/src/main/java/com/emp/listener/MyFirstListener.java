package com.emp.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.java.emp.Employee;

public class MyFirstListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event)  { 

    	List list = new ArrayList();
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt_jdbc","root","root123");
    		PreparedStatement pstmt= con.prepareCall("SELECT * FROM employee");
    		
    		ResultSet res = pstmt.executeQuery();
    		
    		while(res.next())
    		{
    			Employee emp = new Employee();
    			
    			emp.setEmpId(res.getInt(1));
    			emp.setEmpName(res.getString(2));
    			emp.setEmpComp(res.getString(3));
    			emp.setEmpSal(res.getInt(4));
    			
    			list.add(emp);
    		}
    		
    		
    	}
    	catch(Exception e)
    	{
    	e.printStackTrace()	;
    	}
    	ServletContext context = event.getServletContext();
    	context.setAttribute("allData", list);
    	
    	
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
     System.out.println("Project undeployed");	
    }
       
}
