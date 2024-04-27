import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class signin extends HttpServlet {
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO signin VALUES(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, mail);
            ps.setString(3, pass);

            int rs1 = ps.executeUpdate();
 
            RequestDispatcher rd = request.getRequestDispatcher("logSign.html"); 
            rd.include(request,response);
            out.println("<center><h3>Successfully Signed in! Please login.</h3></center>");
        } catch(Exception e) {
            // Handle exceptions appropriately (e.g., log or print error messages)
            out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
