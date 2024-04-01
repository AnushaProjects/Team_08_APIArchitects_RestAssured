package utilities_team08;

import java.util.Properties;

import io.restassured.specification.RequestSpecification;

public class ReusableVariables {
	
	ExcelReader1 read = new ExcelReader1();
	ConfigReader configreader=new ConfigReader();
	
	
	public String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	public String Programpostendpoint="/saveprogram";
	public String createbatchendpoint="/batches";
	public String batchinvalidendpoint="/batche";
	public String updatevalidendpoint="/batches/{batchId}";
	public String Invalidendpointupdate="/batch/{batchId}";
	String bearerToken;
	public static String programId;
    public static String batchId;
	public String authValue;
	public RequestSpecification httpRequest;
	public String GetAllBatch="/batches";
	public String GetByBatchID="/batches/batchId/";
	public String Batch_Invalid_EndPoint="/batches/INVALID/";
	public String GetBatchName="/batches/batchName/";
	public String GetProgramID="/batches/program/";

	public String path=".\\src\\test\\resources\\TestData\\Team_08_API Architects_LMSTestData.xlsx";
}
