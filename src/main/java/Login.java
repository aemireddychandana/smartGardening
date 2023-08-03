import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        
	try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","chandana");
            PreparedStatement ps = con.prepareStatement("select username,password from reg where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            boolean st = rs.next();

        if(st)
        {
        	out.println("WELCOME "+username);
            RequestDispatcher rd = request.getRequestDispatcher("plants.html");
            rd.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rd = request.getRequestDispatcher("login.html");
           rd.include(request, response);
        }
    }  
	catch(Exception e) {
            e.printStackTrace();
        }
	}
}
