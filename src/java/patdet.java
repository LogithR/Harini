/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class patdet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("uname");
        String mail = request.getParameter("email");
        String pass = request.getParameter("pass");

       try {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM appoinmnet WHERE pname = ? ")) {
            ps.setString(1, name);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Successful login
                    String username = rs.getString("uname");
                    

                    response.sendRedirect("index.html"); // Redirect to Home page after successful login
                } else {
                    // Invalid username or password
                    RequestDispatcher rd = request.getRequestDispatcher("logSign.html");
                    rd.include(request, response);
                    out.println("<center><p style=color:red>Invalid username or password. Please try again.</p></center>");
                }
            }
        }
    } catch (Exception ex) {
        // Handle any exceptions
        out.println("An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }
}

}
