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
		String path=System.getProperty("user.dir");
		InputStream input= new FileInputStream(path+"\\src\\main\\java\\config\\config.properties");
		prop.load(input);
		return prop;
		
	}

}
