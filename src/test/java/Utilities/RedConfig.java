package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RedConfig {
	
	Properties properties;
	String path="Properties\\config.properties";
	
	public RedConfig()
	{
		properties=new Properties();
		
		try {
			FileInputStream file = new FileInputStream(path);
			try {
				properties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public String getBrowser()
	{
		String browser=properties.getProperty("browser");
		if(browser!=null)
		{
			return browser;
		}
		else
			throw new RuntimeException("browser not spec. in config file");
	}
	
}
