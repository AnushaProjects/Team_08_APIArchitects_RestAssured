package utilities_team08;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigReader{
	Properties prop;
	public static String configpath="/Users/anushakarthick/NumpyNinja/Anusha_Team8_APIArchitects_RestAssured/src/test/resources/config.properties";
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
}