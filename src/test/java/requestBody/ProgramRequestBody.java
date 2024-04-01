package requestBody;

	import java.util.List;
	import java.util.Map;

	import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payLoad.ProgramPayload;
import utilities.ReusableMethods;

	public class ProgramRequestBody extends ReusableMethods{
		ProgramPayload pp=new ProgramPayload();
		public String createProgramRequest(List<Map<String, String>> hm ) {
		System.out.println(hm.get(0).get("programDescription"));
		System.out.println(hm.get(0).get("programName"));
		System.out.println(hm.get(0).get("programStatus"));

		pp.setProgramDescription(hm.get(0).get("programDescription"));
		String programName="ABCD"+getRandomNumber();
		pp.setProgramName(programName);
		pp.setProgramStatus(hm.get(0).get("programStatus"));
		//pp.setProgramId(hm.get(0).get("programId"));
		JSONObject programBody=new JSONObject(pp);
		return programBody.toString();
		
		}
		
		public ProgramPayload createProgramRequestwithmissingmandatoryfields(List<Map<String, String>> hm, String programId ) {
			 String ProgramName="SDET"+getRandomNumber();
			System.out.println(hm.get(0).get("ProgramDescription"));
			System.out.println(hm.get(0).get("ProgramName"));
			System.out.println(hm.get(0).get("programStatus"));


			pp.setProgramDescription(hm.get(0).get("ProgramDescription"));
			pp.setProgramName(ProgramName);
			//bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
			pp.setProgramStatus(hm.get(0).get("ProgramStatus"));
			pp.setProgramId(programId);
			return pp;
			
		}
		
			
	}
		
		
		


	

