package com.app;

import java.io.*;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Signup() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("postUserId");
        String password = request.getParameter("postPassword");

        if (userId.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in both the User ID and Password fields.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root","1234ANDYsamson+");
            Statement st = conn.createStatement();

            String checkQuery = "SELECT * FROM users WHERE userId='" + userId + "'";
            ResultSet resultSet = st.executeQuery(checkQuery);

            if (resultSet.next()) {
                request.setAttribute("errorMessage", "User name already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
                dispatcher.forward(request, response);
                return;
            } else {
                String insertQuery = "INSERT INTO users (userId, password) VALUES ('" + userId + "', '" + password + "')";
                int rowsAffected = st.executeUpdate(insertQuery);

                if (rowsAffected > 0) {
                    request.setAttribute("successMessage", "Registration successful!");
                    response.sendRedirect(request.getContextPath() + "/Login.jsp?success=true");
                } else {
                    request.setAttribute("errorMessage1", "Registration failed. Please try again.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
                    dispatcher.forward(request, response);
                }
            }

            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            out.print("<h1>Registration failed due to server exception</h1><br>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("<h1>Registration failed due to SQL exception</h1><br>");
            e.printStackTrace();
        }
    }
}