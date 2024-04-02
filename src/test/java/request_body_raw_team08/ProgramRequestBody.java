package request_body_raw_team08;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;

import payload_team08.ProgramPayload;
import payload_team08.UserPayload;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class ProgramRequestBody extends ReusableMethods {  //ReusableVariables
//	ProgramPayload pp=new ProgramPayload();
//	ReusableMethods reuse=new ReusableMethods();
//	ExcelReaderData read = new ExcelReaderData();
//	ConfigReader configreader=new ConfigReader();
//	Properties prop =configreader.readingdata();
//	
//	public ProgramPayload createProgramRequest(List<Map<String, String>> hm ) {
//		
//		LoggerLoad.info("Setting All the Required Fields to the Program Payload:");
//		pp.setProgramDescription(hm.get(0).get("programDescription"));
//		pp.setProgramName(reuse.autoName());		
//		pp.setProgramStatus(hm.get(0).get("programStatus"));
//		pp.setProgramId(hm.get(0).get("programId"));
//		return pp;
//	}
//	
//	public ProgramPayload updateProgramRequest(List<Map<String, String>> hm ) {
//		System.out.println("Inside Update");
//		LoggerLoad.info("Setting All the Required Fields to the Program Payload:");
//		pp.setProgramDescription(hm.get(0).get("updateProgramDescription"));
//			
//		pp.setProgramStatus(hm.get(0).get("updateProgramStatus"));
//
//		return pp;
//	}
//	
//	public ProgramPayload returnUserPayload(String sheet) throws InvalidFormatException, IOException  {
//		
//		List<Map<String, String>> hm=read.getData(path,sheet);
//		return createProgramRequest(hm);
//		
//	}
//	public ProgramPayload returnUserPayloadput(String sheet) throws InvalidFormatException, IOException  {
//		
//		List<Map<String, String>> hm=read.getData(path,sheet);
//		return updateProgramRequest(hm);
//		
//	}
//	public String convertJsonToString(ProgramPayload pm) {
//		JSONObject userBody=new JSONObject(pm);
//		return userBody.toString();
//	}
//	
//	public String negativeUserScenario(String InvalidValue ) throws InvalidFormatException, IOException {
//		returnUserPayload("Program");
//		List<Map<String, String>> hm=read.getData(path,"Program");
//			
//			LoggerLoad.info("Checking the Program creation with input value = " );
//			System.out.println("inside negative");
//			switch(InvalidValue) {
//			case "Program Description":
//				pp.setProgramDescription(hm.get(0).get("InvalidProgramData"));
//				LoggerLoad.info("Invalid Program Description = "+hm.get(0).get("InvalidProgramData")  );
//				break;
//			case "Program Name":
//				pp.setProgramName(hm.get(0).get("InvalidProgramData"));
//				LoggerLoad.info("Invalid Program Name = "+hm.get(0).get("InvalidProgramData"));
//				break;
//			case "Program Status":
//				pp.setProgramStatus(hm.get(0).get("InvalidProgramData"));
//				LoggerLoad.info("Invalid Program Status = "+hm.get(0).get("InvalidProgramData")  );
//				break;
//				
//			}
//			return convertJsonToString(pp);
//
//			
//	 }
	
	//Priyanka
	ProgramPayload pp=new ProgramPayload();
	public ProgramPayload createProgramRequest(List<Map<String, String>> hm ) {
		System.out.println(hm.get(0).get("programDescription"));
		System.out.println(hm.get(0).get("programStatus"));

		pp.setProgramDescription(hm.get(0).get("programDescription"));
		String programName="ABCD"+getRandomNumber();
		pp.setProgramName(programName);
		System.out.println(programName);

		pp.setProgramStatus(hm.get(0).get("programStatus"));
		//JSONObject programBody=new JSONObject(pp);
		return pp;
		
	    }

}
