

import java.util.TimerTask;

public class Testing extends TimerTask
{

	public void run()
	{

                GMailServer sender = new GMailServer("projectdt963@gmail.com", "lodhqpublnrobiut");

	            try {
			    sender.sendMail("WATER YOUR PLANTS!","HEY!IT'S TIME TO WATER YOUR PLANTS","projectdt963@gmail.com",Constants.emailTo);
			}
                   catch (Exception e) {
			     e.printStackTrace();
			}  

				System.out.println("Email Sent Succesfully...");

	        }
	
}