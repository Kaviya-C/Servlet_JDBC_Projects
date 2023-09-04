package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.emp.Employee;


public class MySecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			
			long before = System.currentTimeMillis();
			
			ServletContext context = getServletContext();
			List list=(List)context.getAttribute("allData");
				
			Iterator it = list.iterator();
			
			out.println("<table width=100% border=4>");
			out.println("<caption>Details of Employee</caption>");
			
			while(it.hasNext())
			{
				out.println("<tr>");
				Employee emp = (Employee)it.next();
				out.println("<td>"+emp.getEmpId()+"</td>"
						+ "<td>"+emp.getEmpName()+"</td>"
						+ "<td>"+emp.getEmpComp()+"</td>"
						+ "<td>"+emp.getEmpSal()+"</td>");
				out.println("</tr>");
			}
			

			out.println("</table>");
			long after =System.currentTimeMillis();
			out.println("<br>Total Time Taken By Servlet-1"+(after-before));
			

		
	}

}
