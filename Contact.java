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
 * Servlet implementation class Contact
 */
@WebServlet("/Contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        String table_name = request.getParameter("name");
        String table_email = request.getParameter("email");

        String table_subject = request.getParameter("subject");
        String table_message= request.getParameter("message");
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root", "1234ANDYsamson+");
            Statement st = con.createStatement();

           
            
            

            String insertQuery = "INSERT INTO Contact (table_name, table_email, table_subject, table_message) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, table_name);
            preparedStatement.setString(2, table_email);
         
            preparedStatement.setString(3, table_subject);
            preparedStatement.setString(4, table_message);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "Registration successful!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Contactsuccess.jsp");
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
