package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	public static Properties getProperties() throws IOException
	{
		Properties prop=new Properties();
		InputStream input= new FileInputStream("C:\\Users\\hmohammed25\\OneDrive - DXC Production\\Documents\\ja\\AutomationPlanPOM\\src\\main\\java\\config\\config.properties");
		prop.load(input);
		return prop;
		
	}

}
