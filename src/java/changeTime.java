/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harin
 */
public class changeTime extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    // Get the appnum parameter from the request
    String dname = request.getParameter("dname");
    String qua = request.getParameter("qua");
    String time = request.getParameter("time");
    // Set the result accordingly
    String result = "visited";
    
    try {
            Class.forName("org.postgresql.Driver");
            
            // Establish connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003")) {
                // Prepare the SQL update statement
                PreparedStatement ps = conn.prepareStatement("UPDATE doctor SET time = ? WHERE name = ? and qualification = ?");
                ps.setString(1, time);
                ps.setString(2, dname);
                ps.setString(3, qua);
                
                // Execute the update statement
                int rowsAffected = ps.executeUpdate();
                
                // Check if the update was successful
                if (rowsAffected > 0) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleDOC");
dispatcher.forward(request, response);
                } else {
                    out.println("Failed to update appointment result.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(visit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        out.println("An error occurred: " + e.getMessage());
        e.printStackTrace(out);
    }
}

}
