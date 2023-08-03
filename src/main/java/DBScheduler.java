
import java.io.IOException;
import java.util.Timer;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;



	public class DBScheduler extends HttpServlet{
		private static final long serialVersionUID = 1L;
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    	
	    	response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	       
	      
	      
	        String receiverEmailId= request.getParameter("EmailId");
	        Constants.emailTo=receiverEmailId;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

	             //creating connection with the database
	             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","chandana");
	             PreparedStatement ps = con.prepareStatement("select EmailId from reg where EmailId=?");
	             ps.setString(1,receiverEmailId);
	             ResultSet rs =ps.executeQuery();
	             boolean st = rs.next();
	             if(st)
	             {
				ReadPropertiesFile.readConfig();
				Timer timer = new Timer();
				long  l = 0,m=0;
				String val="",key="";
				String test=Constants.delay;
				String value=Constants.timetoquery;
				

				 val  = value.substring(0,value.length()-1);
				 l = Long.parseLong(val)* 1000;
				

					key  = test.substring(0,test.length()-1);
					m = Long.parseLong(key) * 1000;
				timer.scheduleAtFixedRate(new Testing(),m, l);
				out.println("Sending email");
				RequestDispatcher rd = request.getRequestDispatcher("plants.html");
                rd.include(request, response);
            }
       	 else
       	 {
       		 out.println("Enter valid email");
       		 RequestDispatcher rd = request.getRequestDispatcher("plants.html");
                rd.include(request, response); 
       	 }
	             
			}
			catch(Exception e)
			{

	            e.printStackTrace();
			}
			
	    }
		
		
	    }
	