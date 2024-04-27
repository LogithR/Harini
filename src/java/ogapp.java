import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ogapp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003")) {
              

                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>FOCUS VISION</title>");
                out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
                out.println("    <style type=\"text/css\">");
                out.println("        body {");
                out.println("            background-image: url('app-bg.jpg');");
                out.println("            background-size: cover;");
                out.println("            background-position: center;");
                out.println("            background-repeat: no-repeat;");
                out.println("        }");
                out.println("        .appointment-section{");
                out.println("            margin:0px;");
                out.println("        }");
                out.println("    </style>");
                out.println("</head>");
                out.println("<body>");
                out.println("    <header class=\"clinic-header\">");
                out.println("        <h1 class=\"clinic-name\">Focus Vision</h1>");
                out.println("    </header>");
                out.println("    <div class=\"blur-container\"></div>");
                out.println("    <div class=\"content-container\">");
                out.println("        <section class=\"appointment-section\">");
                out.println("            <div class=\"appointment-form\">");
                out.println("                <form class=\"form-group\" method=\"post\" action=\"ogapp2\" \">");
                out.println("                    <h2>Book an Appointment</h2>");
                out.println("                    Name : <input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
                out.println("                    Email : <input type=\"email\" id=\"email\" name=\"email\" required><br><br>");
                out.println("                    Phone : <input type=\"tel\" id=\"phone\" name=\"phone\" required><br><br>");
                out.println("                    Age : <input type=\"text\" id=\"age\" name=\"age\" required><br><br>");
                out.println("                    Category :<br><br>");
                
                out.println("                    <select name=\"category\" required>");
                out.println("                        <option value=\"\">Select a Category</option>");
                out.println("                        <option value=\"general\">General Checkup</option>");
                out.println("                        <option value=\"opthomology\">opthomology</option>");
                out.println("                        <option value=\"retinal\">retinal</option>");
                out.println("                    </select><br><br>");
                /*out.println("                    Doctor :<br>");
                onsubmit=\"return validateDate()
               String category = request.getParameter("category");
               while (rs.next()){
                    String dname = rs.getString("name");
                    String qualification = rs.getString("qualification");
                    out.println("helo" + category);
 out.println("<input type=\"radio\" id=\"" + dname + "\" name=\"doctor\" value=\"" + dname + "\">");
                        out.println("<label for=\"" + dname + "\">" + dname + "</label><br>");
                }
                
                out.println("<br>");
                out.println("<select id=\"time-slot\" name=\"time-slot\" class=\"dropdown\" required>");
                out.println("                        <option value=\"\">Select a Time Slot</option>");
                out.println("                    </select><br><br>");
                out.println("                    Preferred Date : <input type=\"date\" id=\"date\" name=\"date\" required><br><br>");
                out.println("                    Description : <br><textarea id=\"dis\" name=\"dis\" rows=\"4\" required></textarea><br>");*/
                out.println("                    <button type=\"submit\">Submit</button>");
                out.println("                    <a href=\"logdoc.html\">Cancel</a>");
                out.println("                </form>");
                out.println("            </div>");
                out.println("        </section>");
                out.println("    </div>");

                out.println("    <script type=\"text/javascript\">");
                out.println("        function validateDate() {");
                out.println("            var selectedDate = new Date(document.getElementById(\"date\").value);");
                out.println("            var currentDate = new Date();");
                out.println("            var nextMonth = new Date();");
                out.println("            nextMonth.setMonth(nextMonth.getMonth() + 1);");
                out.println("            if (selectedDate < currentDate || selectedDate >= nextMonth) {");
                out.println("                alert(\"Please select a date within the current month and not in the past.\");");
                out.println("                return false;");
                out.println("            }");
                out.println("            return true;");
                out.println("        }");
                out.println("    </script>");

                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
