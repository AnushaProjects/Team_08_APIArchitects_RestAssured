package request_body_raw_team08;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import payload_team08.ProgramPayload;
import utilities_team08.ReusableMethods;

public class ProgramRequestBody extends ReusableMethods{
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
