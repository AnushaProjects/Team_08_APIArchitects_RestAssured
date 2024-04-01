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
		Properties prop;
		public Properties readingdata() {
			prop = new Properties();

			try {
				 FileInputStream ip = new FileInputStream(".\\src\\test\\java\\utilities\\config.properties");

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

	 props.store(new FileOutputStream(".\\src\\test\\java\\utilities\\config.properties"), null);



	} catch (FileNotFoundException ex) {

	    // file does not exist

	} catch (IOException ex) {

	    // I/O error

	}}	
	}


