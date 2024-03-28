package utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	Properties prop;
	public Properties readingdata() {
		prop = new Properties();

		try {
			 FileInputStream ip = new FileInputStream("/Users/anushakarthick/NumpyNinja/Anusha_Team8_APIArchitects_RestAssured/src/test/resources/config.properties");

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
System.out.println(bearertoken);

try {

    Properties props = new Properties();

    props.setProperty("bearer", bearertoken);

 props.store(new FileOutputStream("/Users/anushakarthick/NumpyNinja/Anusha_Team8_APIArchitects_RestAssured/src/test/resources/config.properties"), null);



} catch (FileNotFoundException ex) {

    // file does not exist

} catch (IOException ex) {

    // I/O error

}}	
}