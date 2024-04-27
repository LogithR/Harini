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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harin
 */
public class notvisit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    // Get the appnum parameter from the request
    String appnum = request.getParameter("appnum");
    
    // Get the value of the button clicked (Visited or Not Visited)
    String buttonClicked = request.getParameter("visited");
    
    // Set the result accordingly
    String result = "Not Visited";
    
    try {
        // Parse appnum to integer
        int appnum1 = Integer.parseInt(appnum);
        
        // Check if appnum is not null before updating the database
        if (appnum != null && !appnum.isEmpty()) {
            // Load the PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            
            // Establish connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003")) {
                // Prepare the SQL update statement
                PreparedStatement ps = conn.prepareStatement("UPDATE appointment SET result = ? WHERE appnum = ?");
                ps.setString(1, result);
                ps.setInt(2, appnum1);
                
                // Execute the update statement
                int rowsAffected = ps.executeUpdate();
                
                // Check if the update was successful
                if (rowsAffected > 0) {
                    out.println("Appointment result updated successfully.");
                } else {
                    out.println("Failed to update appointment result.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(visit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            out.println("Appnum parameter is null or empty.");
        }
    } catch (Exception e) {
        out.println("An error occurred: " + e.getMessage());
        e.printStackTrace(out);
    }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
