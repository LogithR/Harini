import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class app extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Initialize variables to store user input
        String name = request.getParameter("name");
        String mail = request.getParameter("email");
        String ph = request.getParameter("phone");
        String age = request.getParameter("age");
        String doc = request.getParameter("doctor");
        String adate = request.getParameter("date");
        String time = request.getParameter("time"); 
        String des = request.getParameter("dis");
        int age1 = Integer.parseInt(age);
        
        // Initialize variables to count appointments for a doctor
        int appointmentsForDoctor = 0;
        
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Establish a connection to the PostgreSQL database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Feb112003");
            
            // Count appointments for the doctor on the given date
            appointmentsForDoctor = getAppointmentsCountForDoctor(conn, doc, adate);
            
            // Check if the doctor has less than 20 appointments on the given date
            if (appointmentsForDoctor < 20) {
                // Increment the appnum for the new appointment
                //int appnum = getNextAppointmentNumber(conn);
                
                // Prepare and execute SQL query to insert appointment details
                PreparedStatement ps = conn.prepareStatement("INSERT INTO appointment (pname, pmail, phno, dname, description, page, appnum, date, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, mail);
                ps.setString(3, ph);
                ps.setString(4, doc);
                ps.setString(5, des);
                ps.setInt(6, age1);
                ps.setInt(7, appointmentsForDoctor);
                ps.setString(8, adate);
                ps.setString(9, time);
                
                // Execute the update
                int rowsInserted = ps.executeUpdate();
                
                if (rowsInserted > 0) {
                    out.println("<script>alert('Appointment booked successfully!');</script>");
                    out.println("<script>window.location.href='logdoc.html';</script>");
                } else {
                    out.println("<script>alert('Failed to book appointment!');</script>");
                    out.println("<script>window.location.href='logdoc.html';</script>");
                }
                
                // Close the prepared statement
                ps.close();
            } else {
                // Display message if the doctor has reached the maximum appointments for the day
                out.println("<script>alert('Doctor has reached maximum appointments for the day!');</script>");
                out.println("<script>window.location.href='logdoc.html';</script>");
            }
            
            // Close the connection
            conn.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            // Print stack trace or log the exception for debugging purposes
            e.printStackTrace();
            out.println(e);
        } 
    }
    
    // Method to get the count of appointments for a doctor on a specific date
    private int getAppointmentsCountForDoctor(Connection conn, String doctor, String date) {
        int count = 0;
        try {
            // Prepare SQL query to count appointments for a doctor on a given date
            PreparedStatement countPs = conn.prepareStatement("SELECT COUNT(*) FROM appointment WHERE dname = ? AND date = ?");
            countPs.setString(1, doctor);
            countPs.setString(2, date);
            
            // Execute the query
            ResultSet countRs = countPs.executeQuery();
            if (countRs.next()) {
                count = countRs.getInt(1);
            }
            
            // Close the result set and prepared statement
            countRs.close();
            countPs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    // Method to get the next available appointment number
    /*private int getNextAppointmentNumber(Connection conn) {
        int appnum = 1;
        try {
            // Prepare SQL query to get the maximum appnum
            PreparedStatement maxPs = conn.prepareStatement("SELECT MAX(appnum) FROM appointment where ");
            
            // Execute the query
            ResultSet maxRs = maxPs.executeQuery();
            if (maxRs.next()) {
                appnum = maxRs.getInt(1) + 1; // Increment the maximum appnum by 1
            }
            
            // Close the result set and prepared statement
            maxRs.close();
            maxPs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appnum;
    }*/
}
