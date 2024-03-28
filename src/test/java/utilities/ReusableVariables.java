package utilities;

import java.util.Properties;

public class ReusableVariables {
	
	ExcelReader1 read = new ExcelReader1();
	ConfigReader configreader=new ConfigReader();
	
	
	public String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	public String Programpostendpoint="/saveprogram";
	String bearerToken;
	public String authValue;

	public String path=".\\src\\test\\resources\\TestData\\Team_08_API Architects_LMSTestData.xlsx";
}
