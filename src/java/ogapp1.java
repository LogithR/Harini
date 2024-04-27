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

public class ogapp1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "Feb112003")) {

                PreparedStatement ps;
                ps = con.prepareStatement("SELECT name FROM doctor");
                try (ResultSet rs = ps.executeQuery()) {
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
                    out.println(".appointment-section{");
out.println("    margin-top: 100px;");
out.println("}");
                    out.println("    </style>");
                    out.println("    <script>");
                    out.println("        function updateDoctors() {");
                    out.println("            var category = document.querySelector('input[name=\"category\"]:checked').value;");
                    out.println("            var doctors = {");
                    out.println("                'General Checkup': ['Srinivasan: 11am - 1pm', 'Renuka: 9am - 12pm'],");
                    out.println("                'Opthomology': ['Saravanan: 10am - 12pm', 'Venkat: 9:30am - 11:30am'],");
                    out.println("                'Retinal': ['Sindhu: 1pm - 3pm', 'Srinivasan: 11:30am - 1:30pm']");
                    out.println("            };");
                    out.println("            var doctorSelect = document.getElementById('doctor');");
                    out.println("            var timeSlotSelect = document.getElementById('time-slot');");
                    out.println("            doctorSelect.innerHTML = '';"); // Clear existing options
                    out.println("            timeSlotSelect.innerHTML = '';"); // Clear existing options
                    out.println("            var doctorsForCategory = doctors[category];");
                    out.println("            for (var i = 0; i < doctorsForCategory.length; i++) {");
                    out.println("                var doctorAndTime = doctorsForCategory[i].split(':');");
                    out.println("                var doctor = doctorAndTime[0];");
                    out.println("                var timeSlot = doctorAndTime[1];");
                    out.println("                var option = document.createElement('option');");
                    out.println("                option.value = doctor;");
                    out.println("                option.text = doctor;");
                    out.println("                doctorSelect.appendChild(option);");
                    out.println("                var timeOption = document.createElement('option');");
                    out.println("                timeOption.value = timeSlot;");
                    out.println("                timeOption.text = timeSlot;");
                    out.println("                timeSlotSelect.appendChild(timeOption);");
                    out.println("            }");
                    out.println("        }");
                    out.println("    </script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<header class=\"clinic-header\">");
                    out.println("    <h1 class=\"clinic-name\">Focus Vision</h1>");
                    out.println("</header>");
                    out.println("<div class=\"blur-container\"></div>");
                    out.println("<div class=\"content-container\">");
                    out.println("    <section class=\"appointment-section\">");
                    out.println("        <div class=\"appointment-form\">");
                    out.println("            <!-- Appointment form content here -->");
                    out.println("            <form class=\"form-group\" method=\"post\" action=\"index.html\" onsubmit=\"return validateDate()\">");
                    out.println("                <h2>Book an Appointment</h2>");
                    out.println("                Name : <input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
                    out.println("                Email : <input type=\"email\" id=\"email\" name=\"email\" required><br><br>");
                    out.println("                Phone : <input type=\"tel\" id=\"phone\" name=\"phone\" required><br><br>");
                    out.println("                Age : <input type=\"text\" id=\"age\" name=\"age\" required><br><br>");
                    out.println("                Category :"+"<br><br>");
                    out.println("                <input type=\"radio\" id=\"generalCheckup\" name=\"category\" value=\"General Checkup\" onclick=\"updateDoctors()\" required>");
                    out.println("                <label for=\"generalCheckup\">General Checkup</label>");
                    out.println("                <input type=\"radio\" id=\"opthamology\" name=\"category\" value=\"Opthomology\" onclick=\"updateDoctors()\" required>");
                    out.println("                <label for=\"opthamology\">Opthomology</label>");
                    out.println("                <input type=\"radio\" id=\"retinal\" name=\"category\" value=\"Retinal\" onclick=\"updateDoctors()\" required>");
                    out.println("                <label for=\"retinal\">Retinal</label><br><br>");
                    out.println("                Doctor :");
                    out.println("                <select id=\"doctor\" name=\"doctor\" class=\"dropdown\" required>");
                    out.println("                    <option value=\"\">Select a Doctor</option>");
                    while (rs.next()) {
                        String dname = rs.getString("name");
                        out.println("                    <option value='" + dname + "'>" + dname + "</option>");
                    }
                    out.println("                </select><br><br>");
                    out.println("                <select id=\"time-slot\" name=\"time-slot\" class=\"dropdown\" required>");
                    out.println("                    <option value=\"\">Select a Time Slot</option>");
                    out.println("                </select><br><br>");
                    out.println("                Preferred Date : <input type=\"date\" id=\"date\" name=\"date\" required><br><br>");
                    out.println("                Description : <br><textarea id=\"dis\" name=\"dis\" rows=\"4\" required></textarea><br>");
                    out.println("                <button type=\"submit\">Submit</button>");
                    out.println("                <a href=\"index.html\">Cancel</a>");
                    out.println("            </form>");
                    out.println("        </div>");
                    out.println("    </section>");
                    out.println("</div>");
                    out.println("<script type=\"text/javascript\">");
                    out.println("function validateDate() {");
                    out.println("    var selectedDate = new Date(document.getElementById(\"date\").value);");
                    out.println("    var currentDate = new Date();");
                    out.println("    var nextMonth = new Date();");
                    out.println("    nextMonth.setMonth(nextMonth.getMonth() + 1);");
                    out.println("    if (selectedDate < currentDate || selectedDate >= nextMonth) {");
                    out.println("        alert(\"Please select a date within the current month and not in the past.\");");
                    out.println("        return false;");
                    out.println("    }");
                    out.println("    return true;");
                    out.println("}");
                    out.println("</script>");
                    out.println("</body>");
                    out.println("</html>");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
