package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FoodorderUpdate
 */
@WebServlet("/FoodorderUpdate")
public class FoodorderUpdate extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    try {
		        // Establish a database connection
		        Class.forName("com.mysql.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root",
		                "1234ANDYsamson+");

		        // Retrieve the data from the Fooddrinkorder table
		        String query = "SELECT PriceActual_Name FROM Fooddrinkorder";
		        PreparedStatement stmt = con.prepareStatement(query);
		        ResultSet rs = stmt.executeQuery();

		        // Calculate the sum of PriceActual_Name values
		        double sum = 0.0;
		        while (rs.next()) {
		            double price = rs.getDouble("PriceActual_Name");
		            sum += price;
		        }

		        // Close the database connection
		        rs.close();
		        stmt.close();
		        con.close();

		        // Redirect to the new page
		        response.sendRedirect("cartsuccess.jsp?sum=" + sum);
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		    }
		}
}
