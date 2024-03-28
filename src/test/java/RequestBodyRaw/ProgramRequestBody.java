package RequestBodyRaw;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import payLoad.ProgramPayload;

public class ProgramRequestBody {
ProgramPayload pp=new ProgramPayload();
public String createProgramRequest(List<Map<String, String>> hm ) {
	System.out.println(hm.get(0).get("programDescription"));
	System.out.println(hm.get(0).get("programName"));
	System.out.println(hm.get(0).get("programStatus"));

	pp.setProgramDescription(hm.get(0).get("programDescription"));
	pp.setProgramName(hm.get(0).get("programName"));
	pp.setProgramStatus(hm.get(0).get("programStatus"));
	pp.setProgramId(hm.get(0).get("programId"));
	JSONObject programBody=new JSONObject(pp);
	return programBody.toString();
    }


}
