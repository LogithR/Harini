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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harin
 */
public class adminadd extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String dname = request.getParameter("dname");
       String demail = request.getParameter("demail");
        String qua = request.getParameter("qua");
        String dphno = request.getParameter("dpno");
        String time = request.getParameter("time");
        
         try {
                Class.forName("org.postgresql.Driver");

                // Establish connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003")) {
                    // Prepare the SQL delete statement
                    PreparedStatement ps = conn.prepareStatement("insert into doctor (name , qualification,email,time)values(?,?,? ,?)");
                    ps.setString(1, dname);
                    ps.setString(2, qua);
                    ps.setString(3, demail);
                    ps.setString(4, time);
                    // Execute the delete statement
                    int rowsInserted = ps.executeUpdate();

                    // Check if the delete was successful
                    if (rowsInserted > 0) {
                    out.println("<script>alert('ADD  doctor successfully!');</script>");
                    out.println("<script>window.location.href='admin.html';</script>");
                } else {
                    out.println("<script>alert('Failed to book appointment!');</script>");
                    out.println("<script>window.location.href='admin.html';</script>");
                }
                } catch (SQLException ex) {
                    out.println("An error occurred while deleting appointment: " + ex.getMessage());
                }
        } catch (NumberFormatException e) {
            out.println("Invalid appnum parameter: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            out.println("PostgreSQL JDBC driver not found: " + e.getMessage());
        }
    }

    }
