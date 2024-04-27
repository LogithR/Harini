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
public class scheduleDOC extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
 
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003")) {
                PreparedStatement ps;
                ps = con.prepareStatement("SELECT name, qualification , time FROM doctor");
                ResultSet rs = ps.executeQuery();
    
    out.println("<!DOCTYPE html>");
out.println("<html lang=\"en\">");
out.println("<head>");
out.println("    <meta charset=\"UTF-8\">");
out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
out.println("    <title>FOCUS VISION</title>");
out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
out.println("    <style type=\"text/css\">");
out.println("        body {");
out.println("            background-image: url('admin-bg.jpg');");
out.println("            background-size: cover;");
out.println("        }");
out.println("");
out.println("        .appointment-section {");
out.println("            padding: 0;");
out.println("            display: flex;");
out.println("            justify-content: center;");
out.println("            align-items: center;");
out.println("        }");
out.println("");


        out.println("        .content-container {");
      out.println("            margin-left: 230px;");  
out.println("            background-color: rgba(255, 255, 255, 0.8);");
out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);");
out.println("            padding: 40px;");
out.println("            border-radius: 10px;");
out.println("            border: 1px solid #ccc;");
out.println("            max-width: 1000px;");
out.println("            width: 100%;");
out.println("            color: #154406;");
out.println("        }");

out.println("        .appointment-form {");
out.println("            margin-bottom: 400px;");
out.println("            background-color: rgba(255, 255, 255, 0.8);");
out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);");
out.println("            padding: 40px;");
out.println("            border-radius: 10px;");
out.println("            border: 1px solid #ccc;");
out.println("            max-width: 1000px;");
out.println("            width: 100%;");
out.println("            color: #154406;");
out.println("        }");
out.println("    </style>");
out.println("</head>");
out.println("<body>");
out.println("");
out.println("<header class=\"clinic-header\">");
out.println("        <center>");
out.println("    <h1 class=\"clinic-name\">Focus Vision</h1>");
out.println("    <nav class=\"clinic-nav\">");
out.println("        <ul>");
out.println("            <li><a href=\"admin.html\">ADD/DELETE</a></li>");
out.println("            <li><form method=\"post\" action=\"docReport\"><input type=\"submit\" value=\"Scheduling\"></form></li>");
out.println("<li><a href='index.html'>LOGOUT</a></li>");
out.println("        </ul>");
out.println("    </nav>");
out.println("        </center>");
out.println("</header>");
out.println("");
out.println("<div class=\"content-container\">");
out.println("<center>");
out.println("            <form  method=\"post\" action=\"changeTime\">");
while (rs.next()) {
    String dname = rs.getString("name");
    String qualification = rs.getString("qualification");
    String time = rs.getString("time");
    
    // Check if the doctor's qualification matches the selected category
            out.println("<b>Name :</b>" + dname + " ");
 out.println("<b>Qualification :</b>" + qualification+"  ");              
 out.println("<b>Time :</b>" + time + "  " +"<br><br>");
}
out.println("</center>");
out.println("</div>");
            out.println("    <section class=\"appointment-section\">");
            out.println("        <div class=\"appointment-form\">");
            out.println("            <form class=\"form-group\" method=\"post\" action=\"changeTime\">");
            out.println("                <h2>Add Doctor</h2>");
            out.println("                Doctor name: <input type=\"text\" name=\"dname\" required><br><br>");
            out.println("                Doctor qualification: <input type=\"text\" name=\"qua\" required><br><br>");
            out.println("                Time: <input type=\"text\" name=\"time\" required><br><br>");
            out.println("                <button type=\"submit\">Submit</button>");
            out.println("            </form>");
            out.println("        </div>");
            out.println("    </section>");
            out.println("</div>");

out.println("");
out.println("</body>");
out.println("</html>");

}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

