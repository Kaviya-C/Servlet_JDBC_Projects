package com.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		int rollNo = Integer.parseInt(request.getParameter("rollno"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SERVLET", "root", "root123");

			PreparedStatement pstmt = con.prepareStatement("Select * from Student where rollNo=?");

			pstmt.setInt(1, rollNo);

			out.println("<table width=100% border=2>");

			out.println("<caption> Result: </caption>");

			ResultSet res = pstmt.executeQuery();

			ResultSetMetaData rd = res.getMetaData();

			int count = rd.getColumnCount();

			out.print("<tr>");

			for (int i = 1; i <= count; i++) {
				out.print("<th>" + rd.getColumnName(i) + "</th>");

			}
			out.print("</tr>");

			while (res.next()) {
				out.print("<tr><td>" + res.getInt(1) + "</td>" + "<td>" + res.getString(2) + "</td>" + "<td>"
						+ res.getInt(3) + "</td></tr>");
			}
			out.print("</table>");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
