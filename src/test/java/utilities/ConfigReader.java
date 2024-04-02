
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;
	public static String configpath = ".\\src\\test\\java\\utilities\\config.properties";

	public Properties readingdata() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(configpath);
			try {
				prop.load(ip);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return prop;
		}
	
	public void writingdata(String bearertoken) {
		try {
			Properties props = new Properties();
			props.setProperty("bearer", bearertoken);
			props.store(new FileOutputStream(configpath), null);
		} catch (FileNotFoundException ex) {
			// file does not exist

		} catch (IOException ex) {
			// I/O error
		}			
	}	
	
	public void writingdata(String configKey, String configValue) {		
		 Properties props = new Properties();
		 props=readingdata();

	try {
	    props.setProperty(configKey, configValue);	 
	props.store(new FileOutputStream(configpath), null);
	} catch (FileNotFoundException ex) {
	    // file does not exist
	} catch (IOException ex) {
	    // I/O error
	}}	
	
	public String getUserIdWithAAllField() {
		String userIdWithAAllField = prop.getProperty("user_id_with_All_field");
		return userIdWithAAllField;
	}
	
	public String getEmaildId() {
		String emailIdUsedForCreatingUserId = prop.getProperty("email_id_used_for_creating_userId");
		return emailIdUsedForCreatingUserId;
	}
}