package com.app;

import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TableorderServlet
 */
@WebServlet("/TableorderServlet")
public class TableorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String table_name = request.getParameter("Tablename");
        String table_email = request.getParameter("Tableemail");

        String table_date_input = request.getParameter("TableDate"); // Modified variable name
        String table_select = request.getParameter("Tableselect");
        String table_message = request.getParameter("Tablemessage");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root", "1234ANDYsamson+");
            Statement st = con.createStatement();

           
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            java.util.Date table_date = formatter.parse(table_date_input);

            String insertQuery = "INSERT INTO Tableorder (table_name, table_email, table_date, table_select, table_message) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, table_name);
            preparedStatement.setString(2, table_email);
            preparedStatement.setTimestamp(3, new Timestamp(table_date.getTime()));
            preparedStatement.setString(4, table_select);
            preparedStatement.setString(5, table_message);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("successMessage", "Registration successful!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booksuccess.jsp");
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
