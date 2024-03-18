package com.app;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

@WebServlet("/FoodDrinkOrderServlet") 
public class FoodDrinkOrderServlet extends HttpServlet { 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 

        try { 
            // Establish a database connection 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant1", "root", "1234ANDYsamson+"); 

            // Execute SQL query 
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Fooddrinkorder"); 
            ResultSet rs = ps.executeQuery(); 

        
            
            
            
            // Display data on HTML page 
            while (rs.next()) { 
                out.println("<div class=\"col-lg-3 col-sm-6 wow fadeInUp\" data-wow-delay=\"0.1s\">");
                out.println("<div class=\"service-item rounded pt-3\">");
                out.println(" <div class=\"p-4\">");
                out.println("   <center> <i class=\"fa fa-3x fa-user-tie text-primary mb-4\"></i>  </center> ");
                
                String FoodActual_Name = rs.getString("FoodActual_Name");
                String priceActual_Name = rs.getString("priceActual_Name");
            	String foodName = rs.getString("food_name");
                String email = rs.getString("food_email");
                String destination = rs.getString("food_destination");
                String date = rs.getString("food_date");
                String select = rs.getString("food_select");
                String message = rs.getString("food_message");
                String phone = rs.getString("food_phone");
                int foodId = rs.getInt("ID");
              
                //out.print("name: " + rs.getString("food_name") + ", email: " + rs.getString("food_email") + ", destination: " + rs.getString("food_destination")+ ", select: " + rs.getString("food_select")+ ", message: " + rs.getString("food_message") + ", phone: " + rs.getString("food_phone") + "<br>"); 
                
                out.println("<p>FOOD NAME : - "+FoodActual_Name+"</p>");
                out.println("<p>FOOD Price : - "+priceActual_Name+"</p>");
                out.println("<p>YOUR NAME : - "+ foodName +"</p>");
                out.println("<p>YOUR EMAIL : - "+ email +"</p>");
                out.println("<p>YOUR DESTINATION : - "+destination+"</p>");
                out.println("<p>DELIVERY DATE : - "+date+"</p>");
                out.println("<p>NUMBER OF ITEM : - "+select+"</p>");
                out.println("<p>ORDER MESSAGE : - "+message+"</p>");
                out.println("<p>YOUR PHONE NO : - "+phone+"</p>");
                out.println("<center></h6><button href=\"#\" class=\"btn btn-lg\" style=\"margin: 4px 2px; display:inline; background-color:#fc4714;\" onclick=\"deleteData('" + foodId + "')\"><i class=\"fa fa-trash\"></i></button></center>");
                out.println("<center></center>");
                
                out.println("</div>");
                
                out.println("</div>");
                out.println(" </div>");
                
            } 
           

            // Close resources 
            con.close(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 
