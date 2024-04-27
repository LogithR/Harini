package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("uname");
        String pass = request.getParameter("pass");

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "Feb112003")) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM signin WHERE uname = ? AND pass = ?");
                ps.setString(1, name);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery(); // Execute the query and get the result set
                
                HttpSession session = request.getSession();
                session.setAttribute("name", name); // Example: Get pname from database
                if("admin".equals(name) && "admin123".equals(pass)){
                   response.sendRedirect("admin.html");
                }
                if (rs.next()) {
                    // Successful login
                    PreparedStatement ps2 = con.prepareStatement("SELECT COUNT(*) FROM appointment WHERE pname = ?");
                    ps2.setString(1, name);

                    ResultSet rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        // Process the second ResultSet here
                    }
                    rs2.close(); // Close the second ResultSet
                    ps2.close();
                    response.sendRedirect("logdoc.html");
// Close the second PreparedStatement

                } 
                else {
                    // Invalid username or password
                    RequestDispatcher rd = request.getRequestDispatcher("logSign.html");
                    rd.include(request, response);
                    out.println("<center><p style=color:red>Invalid username or password. Please try again.</p></center>");
                }

                rs.close(); // Close the first ResultSet
                ps.close(); // Close the first PreparedStatement
            }
        } catch (Exception ex) {
            out.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
