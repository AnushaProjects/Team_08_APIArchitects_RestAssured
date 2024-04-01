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

public class ProgramRequestBody extends ReusableVariables {
	ProgramPayload pp=new ProgramPayload();
	ReusableMethods reuse=new ReusableMethods();
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	
	public ProgramPayload createProgramRequest(List<Map<String, String>> hm ) {
		
		LoggerLoad.info("Setting All the Required Fields to the Program Payload:");
		pp.setProgramDescription(hm.get(0).get("programDescription"));
		pp.setProgramName(reuse.autoName());		
		pp.setProgramStatus(hm.get(0).get("programStatus"));
		pp.setProgramId(hm.get(0).get("programId"));
		return pp;
	}
	
	public ProgramPayload returnUserPayload(String sheet) throws InvalidFormatException, IOException  {
		
		List<Map<String, String>> hm=read.getData(path,sheet);
		return createProgramRequest(hm);
		
	}
	public String convertJsonToString(ProgramPayload pm) {
		JSONObject userBody=new JSONObject(pm);
		return userBody.toString();
	}
	
	

}
