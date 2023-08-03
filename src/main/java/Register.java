import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	response.setContentType("text/html");  
    	PrintWriter out = response.getWriter();  
    	          
    	String n=request.getParameter("username");  
    	String p=request.getParameter("password");  
    	String e=request.getParameter("EmailId"); 
    	String c=request.getParameter("phno"); 
    	String r=request.getParameter("gender");  
        
	try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","chandana");
            PreparedStatement ps=con.prepareStatement(  
            		"insert into reg values(?,?,?,?,?)");  
            		  
            		ps.setString(1,n);  
            		ps.setString(2,p);  
            		ps.setString(3,e);  
            		ps.setString(4,c);  
            		ps.setString(5,r);  
            		          
            		int i=ps.executeUpdate();  
            		
            		  

            		if(i>0)
                    {
            			out.print("You are successfully registered...");
                        RequestDispatcher rd = request.getRequestDispatcher("login.html");
                        rd.forward(request, response);
                    }
                    else
                    {
                       out.println("Required to fill all the fields ");
                       RequestDispatcher rd = request.getRequestDispatcher("Register.html");
                       rd.include(request, response);
                    }

    }  
	catch(Exception e1) {
            e1.printStackTrace();
        }
	}
}
