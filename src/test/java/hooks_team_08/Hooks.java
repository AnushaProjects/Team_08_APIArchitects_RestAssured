package hooks_team_08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.cucumber.java.BeforeAll;
import utilities_team08.ConfigReader;
import utilities_team08.LoggerLoad;

public class Hooks {
	@BeforeAll
	public static void beforeAll() throws IOException 
	{
		LoggerLoad.info("************** Starting the execution ********************");
		
		 Properties props = new Properties();
		 FileInputStream ip = new FileInputStream(ConfigReader.configpath);
		 props.load(ip);
		 System.out.println(props);
		 LoggerLoad.info("Before clearing the property file : "+props);
		 FileOutputStream op = new FileOutputStream(ConfigReader.configpath);
		 props.clear();
		 LoggerLoad.info("After clearing the property file : "+  props);
		 props.load(ip);
		 props.store(op, null);
		 LoggerLoad.info("Saved the cleaned Property file : "+  props);
		 
	}
}