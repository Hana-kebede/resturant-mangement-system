package com.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
        	// Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1",
                    "root", "1234ANDYsamson+");

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Fooddrinkorder");

            StringBuilder htmlBuilder = new StringBuilder();

            // Iterate through the result set and build the HTML dynamically
            while (resultSet.next()) {
            	String FoodActualName = resultSet.getString("FoodActual_Name");
            	String foodEmail = resultSet.getString("food_email");
            	String foodName = resultSet.getString("food_name");
            	String fooddestination = resultSet.getString("food_destination");
            
            	String foodselect = resultSet.getString("food_select");
            	String description = resultSet.getString("food_message");
            	String foodphone = resultSet.getString("food_phone");
            	
            	
            	htmlBuilder.append("<div class=\"col-md-4\">");
            	htmlBuilder.append("<div class=\"card\">");
            	htmlBuilder.append("<div class=\"card-body\">");
            	htmlBuilder.append("<h5 class=\"card-title\">").append(FoodActualName).append("</h5>");
            	htmlBuilder.append("<p class=\"card-title\">").append(foodName).append("</p>");
            	htmlBuilder.append("<p class=\"card-text\">").append(foodEmail).append("</p>");
            	htmlBuilder.append("<p class=\"card-text\">").append(fooddestination).append("</p>");
            	htmlBuilder.append("<p class=\"card-text\">").append(description).append("</p>");
            	htmlBuilder.append("<p class=\"card-text\">").append(foodselect).append("</p>");
            	
            	htmlBuilder.append("<p class=\"card-text\">").append(foodphone).append("</p>");
            
            	htmlBuilder.append("</div>");
            	htmlBuilder.append("</div>");
            	htmlBuilder.append("</div>");
            }

            resultSet.close();
            statement.close();
            connection.close();

            String html = htmlBuilder.toString();
            out.println("<script>console.log('" + html + "');</script>"); // Output the generated HTML to the console for debugging
            out.println("<script>document.getElementById('serviceItems').innerHTML = \"" + html + "\";</script>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
