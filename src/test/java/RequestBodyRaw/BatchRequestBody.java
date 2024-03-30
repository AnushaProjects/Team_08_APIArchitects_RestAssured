package RequestBodyRaw;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import payLoad.BatchPayload;
import payLoad.ProgramPayload;
import utilities.ReusableMethods;

public class BatchRequestBody extends ReusableMethods{
	
	BatchPayload bp=new BatchPayload();
	
	public String createBatchRequest(List<Map<String, String>> hm, String programId ) {
		  String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchDescription"));
		System.out.println(hm.get(0).get("BatchNoOfClasses"));
		System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		JSONObject batchBody=new JSONObject(bp);
		return batchBody.toString();
		
	}
	public String createBatchRequestwithexistingdata(List<Map<String, String>> hm, String programId,String BatchName ) {
		 //BatchName="SDET"+getRandomNumber();
		//System.out.println(hm.get(0).get("BatchDescription"));
		//System.out.println(hm.get(0).get("BatchNoOfClasses"));
		//System.out.println(hm.get(0).get("BatchStatus"));
		System.out.println(BatchName);


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		JSONObject batchBody=new JSONObject(bp);
		return batchBody.toString();
		
	}

	public String createBatchRequestwithmissingmandatoryfields(List<Map<String, String>> hm, String programId ) {
		 String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchDescription"));
		System.out.println(hm.get(0).get("BatchNoOfClasses"));
		System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		//bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		JSONObject batchBody=new JSONObject(bp);
		return batchBody.toString();
		
	}
	public String createBatchRequestwithmissingadditionalfields(List<Map<String, String>> hm, String programId ) {
		String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchNoOfClassesmissingadditonalfield"));
		System.out.println(hm.get(0).get("BatchStatusmissingadditonalfield"));

		bp.setBatchDescription(hm.get(0).get("BatchDescriptionmissingadditionalfield"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClassesmissingadditonalfield"));
		bp.setBatchStatus(hm.get(0).get("BatchStatusmissingadditonalfield"));
		bp.setProgramId(programId);
		JSONObject batchBody=new JSONObject(bp);
		return batchBody.toString();
		
	}
	public String createBatchRequestwithinactiveprogramid(List<Map<String, String>> hm, String programId ) {
		String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchDescription"));
		System.out.println(hm.get(0).get("BatchNoOfClasses"));
		System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId("000");
		JSONObject batchBody=new JSONObject(bp);
		return batchBody.toString();
		
	}
}
