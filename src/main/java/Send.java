import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Send extends HttpServlet{
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
   
	
		
	 String senderEmailId="projectdt963";
	 String senderPassword="lodhqpublnrobiut";
	 String emailSMTPserver="smtp.gmail.com";
	String emailServerPort="465";
	String receiverEmailId=request.getParameter("EmailId"); 
	 String emailSubject="WATER THE PLANTS";
	 String emailBody="HEY!IT'S TIME TO WATER YOUR POTATO PLANT";
    
        Properties properties=new Properties();
        properties.put("mail.smtp.user",senderEmailId );
        properties.put("mail.smtp.host",emailSMTPserver );
        properties.put("mail.smtp.port",emailServerPort );
        properties.put("mail.smtp.starttls.enable","true" );
        properties.put("mail.smtp.auth","true" );
        properties.put("mail.smtp.socketFactory.port",emailServerPort );
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory" );
        properties.put("mail.smtp.socketFactory.fallback","false" );
        try {
        	

              
               
        	
        	Session ss = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmailId, senderPassword);
                }
            });
        	 Class.forName("com.mysql.jdbc.Driver");

             //creating connection with the database
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","chandana");
             PreparedStatement ps = con.prepareStatement("select EmailId from reg where EmailId=?");
             ps.setString(1,receiverEmailId);
             ResultSet rs =ps.executeQuery();
             boolean st = rs.next();
        	MimeMessage msg=new MimeMessage(ss);
        	msg.setText(emailBody);
        	msg.setSubject(emailSubject);
        	msg.setFrom(new InternetAddress(senderEmailId));
        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailId));
        	 if(st)
             {
        	Transport.send(msg);
        		out.println("Message send");
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
	 

