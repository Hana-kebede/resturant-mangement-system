package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Foodorder
 */
@WebServlet("/Foodorder")
public class Foodorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Foodorder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String FoodActual_Name = request.getParameter("FoodActualName");
        String PriceActual_Name = request.getParameter("priceActualName");
        String food_name = request.getParameter("Foodname");
        String food_email = request.getParameter("Foodemail");
        String food_destination = request.getParameter("Fooddestination");
        String food_date_input = request.getParameter("FoodDate"); // Modified variable name
        
        String food_select = request.getParameter("Foodselect");
        String food_message = request.getParameter("Foodmessage");
        String food_phone = request.getParameter("Foodphone");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root", "1234ANDYsamson+");
            Statement st = con.createStatement();

           
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            java.util.Date food__date = formatter.parse(food_date_input);

            String insertQuery = "INSERT INTO Fooddrinkorder (FoodActual_Name,PriceActual_Name,  food_name, food_email, food_destination, food_date, food_select, food_message, food_phone) VALUES (?, ?,  ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

            preparedStatement.setString(1, FoodActual_Name);
            preparedStatement.setString(2, PriceActual_Name);
            preparedStatement.setString(3, food_name);
            preparedStatement.setString(4, food_email);
            preparedStatement.setString(5, food_destination);
            preparedStatement.setTimestamp(6, new Timestamp(food__date.getTime()));
            preparedStatement.setString(7, food_select);
            preparedStatement.setString(8, food_message);
            preparedStatement.setString(9, food_phone);
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "Registration successful!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
                dispatcher.forward(request, response);
                return;
               
            } else {
                request.setAttribute("errorMessage1", "Registration failed. Please try again.");
            }
            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            out.print("<h1>Registration failed due to server exception</h1><br>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("<h1>Registration failed due to SQL exception</h1><br>");
            e.printStackTrace();
        } catch (ParseException e) {
            out.print("<h1>Registration failed due to date parsing exception</h1><br>");
            e.printStackTrace();
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
