package appointment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class appointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "Feb112003")) {

                PreparedStatement ps;
                if (isDoctor(name)) {
                    ps = con.prepareStatement("SELECT pname,page, dname, date, time, appnum,result FROM appointment "
                            + "WHERE dname = ?");
                    ps.setString(1, name);
                } else {
                    ps = con.prepareStatement("SELECT pname,page, dname, date, time, appnum , result FROM appointment "
                            + "WHERE pname = ?");
                    ps.setString(1, name);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html lang=\"en\">");
                    out.println("<head>");
                    out.println("    <meta charset=\"UTF-8\">");
                    out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                    out.println("    <title>FOCUS VISION</title>");
                    out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
                    out.println("    <style>");
                    out.println("        .appointment-box {");
                    out.println("            border: 1px solid #ccc;");
                    out.println("            padding: 10px;");
                    out.println("            margin-bottom: 20px;");
                    out.println("        }");
                    out.println(".appointment-box {");
                    out.println("    color: white;");
                    out.println("    width: 1000px;");
                    out.println("    margin: 20px auto; /* Center the form horizontally */");
                    out.println("    border-radius: 10px;");
                    out.println("    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); /* Shadow effect */");
                    out.println("}");
                    out.println(".app-box{");
                    out.println("font-size:20px;");
                    out.println("margin-left: 100px;");
                    out.println("}");
                    out.println("        .appointment1-box {");
                    out.println("            border: 1px solid #ccc;");
                    out.println("            padding: 10px;");
                    out.println("            margin-bottom: 20px;");
                    out.println("        }");
                    out.println(".appointment1-box {");
                    out.println("    color: grey;");
                    out.println("    width: 1000px;");
                    out.println("    margin: 20px auto; /* Center the form horizontally */");
                    out.println("    border-radius: 12px ;");
                    out.println("    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); /* Shadow effect */");
                    out.println("}");
                    out.println(".app1-box{");
                    out.println("font-size:20px;");
                    out.println("margin-left: 100px;");
                    out.println("}");
                    out.println("        .appointment2-box {");
                    out.println("            border: 1px solid #ccc;");
                    out.println("            padding: 10px;");
                    out.println("            margin-bottom: 20px;");
                    out.println("        }");
                    out.println(".appointment2-box {");
                    out.println("    color: red;");
                    out.println("    width: 1000px;");
                    out.println("    margin: 20px auto; /* Center the form horizontally */");
                    out.println("    border-radius: 12px ;");
                    out.println("    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); /* Shadow effect */");
                    out.println("}");
                    out.println(".app2-box{");
                    out.println("font-size:20px;");
                    out.println("margin-left: 100px;");
                    out.println("}");
                    out.println(".future-appointment {");
                    out.println("    background-color: green;");
                    out.println("}");
                    out.println(".past-appointment {");
                    out.println("    background-color: red;");
                    out.println("}");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<header class=\"clinic-header\">");
                    out.println("    <h1 class=\"clinic-name\">Focus Vision</h1>");
                    out.println("    <nav class=\"clinic-nav\">");
                    out.println("        <ul>");
                    out.println("            <li><a href=\"logdoc.html\">DOCTORS</a></li>");
                    out.println("<li><a><form action=\"ogapp\" method=\"post\">");
                    out.println("    <input type=\"submit\" value=\"Book\" style=\"background-color: transparent; border: none; cursor: pointer; padding: 0; margin: 0; color: #154406; text-decoration: underline; font-size: 25px; font-family: 'Lucida Console', monospace;\">");
                    out.println("</form></a></li>");
                    out.println("<li><a><form action=\"appointment\" method=\"post\">");
                    out.println("    <input type=\"submit\" value=\"Appointments\" style=\"background-color: transparent; border: none; cursor: pointer; padding: 0; margin: 0; color: #154406; text-decoration: underline; font-size: 25px; font-family: 'Lucida Console', monospace;\">");
                    out.println("</form></a></li>");
                    
                    out.println("            <li><a href=\"index.html\">LOGOUT</a></li>");
                    out.println("        </ul>");
                    out.println("    </nav>");
                    out.println("</header>");
                    out.println("<div class=\"appointments\">");

                    boolean hasRecords = false;
                    while (rs.next()) {
                        hasRecords = true;
                        String pname = rs.getString("pname");
                        String dname = rs.getString("dname");
                        String age = rs.getString("page");
                        String dateStr = rs.getString("date");
                        String timeStr = rs.getString("time");
                         String appnum = rs.getString("appnum");
                         String result =rs.getString("result");
                        // Parse appointment date string into Date object
                        request.setAttribute("appnum", appnum);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date appointmentDate = Date.valueOf(dateStr);

                        // Get the current date
                        Date currentDate = new Date(System.currentTimeMillis());

                        // Determine the CSS class based on appointment date
                        String cssClass = "";
                        if (appointmentDate.after(currentDate)) {
                            cssClass = "future-appointment";
                        } else {
                            cssClass = "past-appointment";
                        }

                        if (isDoctor(name)) {
                           if ("visited".equals(result)) {
                        out.println("<div class=\"appointment1-box " + cssClass + "\">");
                        out.println("<div class=\"app1-box\">");
                            out.println("    <p>Patient Name: " + pname + "</p>");
                            out.println("    <p>Appnum: " +appnum + "</p>");
                            out.println("    <p>Age: " + age + "</p>");
out.println("    <p>Date: " + dateStr + "</p>");
                        out.println("    <p>Time: " + timeStr + "</p>");
                         out.println("<p>Visited</p>");
out.println("</div>");
                        out.println("</div>");

                           }
                           else {
                           out.println("<div class=\"appointment-box " + cssClass + "\">");
                        out.println("<div class=\"app-box\">");
                            out.println("    <p>Patient Name: " + pname + "</p>");
                            out.println("    <p>Appnum: " +appnum + "</p>");
                            out.println("    <p>Age: " + age + "</p>");
out.println("    <p>Date: " + dateStr + "</p>");
                        out.println("    <p>Time: " + timeStr + "</p>");
                        out.println("<form method=\"post\" action=\"visit\">");
out.println("<input type=\"hidden\" name=\"appnum\" value=\"" + appnum + "\">");
out.println("<input type=\"hidden\" name=\"date\" value=\"" + dateStr + "\">");
out.println("<input type=\"submit\" name=\"visit\" value=\"Visited\">");
out.println("</form>");  

out.println("</div>");
                        out.println("</div>");
                           }
                        } else {
                           if ("visited".equals(result)) {
                            out.println("<div class=\"appointment1-box " + cssClass + "\">");
                            out.println("<div class=\"app1-box\">");
                            out.println("    <p>Patient Name: " + pname + "</p>");
                            out.println("    <p>Doctor Name: " + dname + "</p>");
                            out.println("    <p>Date: " + dateStr + "</p>");
                        out.println("    <p>Time: " + timeStr + "</p>");
                        out.println("    <p>App no: " + appnum + "</p>");
                        out.println("<p>Visited</p>");
                        out.println("</div>");
                        out.println("</div>");
                            }
                          
                           else{
                            out.println("<div class=\"appointment-box " + cssClass + "\">");
                            out.println("<div class=\"app-box\">");
                            out.println("    <p>Patient Name: " + pname + "</p>");
                            out.println("    <p>Doctor Name: " + dname + "</p>");
                            out.println("    <p>Date: " + dateStr + "</p>");
                        out.println("    <p>Time: " + timeStr + "</p>");
                        
                        out.println("    <p>App no: " + appnum + "</p>");
                        out.println("<form method=\"post\" action=\"delete\">");
out.println("<input type=\"hidden\" name=\"appnum\" value=\"" + appnum + "\">");
out.println("<input type=\"hidden\" name=\"name\" value=\"" + pname + "\">");
out.println("<input type=\"hidden\" name=\"doctor\" value=\"" + dname + "\">");
out.println("<input type=\"submit\" name=\"delete\" value=\"DELETE\">");
out.println("</form>"); 
                                out.println("</div>");
                        out.println("</div>");
                           }
                        }
                        
                        out.println("</div>");
                        out.println("</div>");
                    }

                    if (!hasRecords) {
                        out.println("<p>No appointments found.</p>");
                    }
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            out.println("An error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean isDoctor(String name) {
        return name.equals("Srinivasan") || name.equals("Saravanan") || name.equals("Renuka")
                || name.equals("Sindhu") || name.equals("Venkat");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
