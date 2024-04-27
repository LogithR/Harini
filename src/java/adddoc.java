import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adddoc extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("add");
        String action1 = request.getParameter("del");
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
        out.println("        app-box {");
        out.println("            color: white;");
        out.println("            width: 500px;");
        out.println("            margin: 100px auto;");
        out.println("            background-color: rgba(0, 0, 0, 0.5);");
        out.println("            backdrop-filter: blur(5px);");
        out.println("            border-radius: 10px;");
        out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header class=\"clinic-header\">");
        out.println("    <h1 class=\"clinic-name\">Focus Vision</h1>");
        out.println("    <nav class=\"clinic-nav\">");
        out.println("        <ul>");
        out.println("             <li><a href=\"admin.html\">ADD/DELETE</a></li>");
        out.println("            <li><form method=\"post\" action=\"scheduleDOC\"><input type=\"submit\" value=\"Scheduling\"></form></li>");
        out.println("<li><a href='index.html'>LOGOUT</a></li>");
        out.println("        </ul>");
        out.println("    </nav>");
        out.println("</header>");

        if ("add".equals(action)) {
            out.println("<div class=\"content-container\">");
            out.println("    <section class=\"appointment-section\">");
            out.println("        <div class=\"appointment-form\">");
            out.println("            <form class=\"form-group\" method=\"post\" action=\"adminadd\">");
            out.println("                <h2>Add Doctor</h2>");
            out.println("                Doctor name: <input type=\"text\" name=\"dname\" required><br><br>");
            out.println("                Doctor email: <input type=\"email\" name=\"demail\" required><br><br>");
            out.println("                Doctor qualification: <input type=\"text\" name=\"qua\" required><br><br>");
            out.println("                Doctor phone number: <input type=\"text\" name=\"dpno\" required><br><br>");
            out.println("                Time: <input type=\"text\" name=\"time\" required><br><br>");

            out.println("                <button type=\"submit\">Submit</button>");
            out.println("            </form>");
            out.println("        </div>");
            out.println("    </section>");
            out.println("</div>");
        } else if ("del".equals(action1)) {
            out.println("<div class=\"content-container\">");
            out.println("    <section class=\"appointment-section\">");
            out.println("        <div class=\"appointment-form\">");
            out.println("            <form class=\"form-group\" method=\"post\" action=\"admindelete\">");
            out.println("                <h2>DELETE Doctor</h2>");
            out.println("                Doctor name: <input type=\"text\" name=\"dname\" required><br><br>");
            out.println("                Doctor email: <input type=\"text\" name=\"demail\" required><br><br>");
            out.println("                Doctor qualification: <input type=\"text\" name=\"qua\" required><br><br>");
            out.println("                <button type=\"submit\">Submit</button>");
            out.println("            </form>");
            out.println("        </div>");
            out.println("    </section>");
            out.println("</div>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
