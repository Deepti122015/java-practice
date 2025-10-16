import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empIdParam = request.getParameter("empid");

        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String pass = "your_password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();

            String query;
            if (empIdParam != null && !empIdParam.isEmpty()) {
                query = "SELECT * FROM Employee WHERE EmpID = " + empIdParam;
            } else {
                query = "SELECT * FROM Employee";
            }

            ResultSet rs = stmt.executeQuery(query);

            out.println("<h2>Employee Records</h2>");
            out.println("<form action='EmployeeServlet' method='get'>");
            out.println("Search by ID: <input type='text' name='empid'>");
            out.println("<input type='submit' value='Search'>");
            out.println("</form><br>");

            out.println("<table border='1'>");
            out.println("<tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("EmpID") + "</td>");
                out.println("<td>" + rs.getString("Name") + "</td>");
                out.println("<td>" + rs.getDouble("Salary") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
