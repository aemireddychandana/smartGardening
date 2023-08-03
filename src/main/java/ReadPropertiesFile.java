

import java.io.FileInputStream;
import java.util.Properties;


public class ReadPropertiesFile
{
	public  static void readConfig() throws Exception
	{
		try
		{

		    	   

		    Constants.delay = "2s";
		    Constants.timetoquery = "10s";
		    Constants.setFrom = "projectdt963@gmail.com";
		    Constants.setPassword = "lodhqpublnrobiut";  		   

		}
		catch(Exception e)
		{
            throw new Exception(e);
		}

	}

}