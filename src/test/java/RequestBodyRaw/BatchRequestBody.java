package RequestBodyRaw;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.PPMT;
import org.json.JSONObject;

import payLoad.BatchPayload;
import payLoad.ProgramPayload;
import utilities.ReusableMethods;

public class BatchRequestBody extends ReusableMethods{
	
	BatchPayload bp=new BatchPayload();
	ProgramPayload pp=new ProgramPayload();
	
	
	public BatchPayload createBatchRequest(List<Map<String, String>> hm, String programId ) {
		  String BatchName="SDET"+getRandomNumber();
		//System.out.println(hm.get(0).get("BatchDescription"));
		//System.out.println(hm.get(0).get("BatchNoOfClasses"));
		//System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		return bp;
	}
	public BatchPayload createBatchRequestwithexistingdata(List<Map<String, String>> hm, String programId,String BatchName ) {
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
		return bp;
		
	}

	public BatchPayload createBatchRequestwithmissingmandatoryfields(List<Map<String, String>> hm, String programId ) {
		 String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchDescription"));
		System.out.println(hm.get(0).get("BatchNoOfClasses"));
		System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		//bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		return bp;
		
	}
	public BatchPayload createBatchRequestwithmissingadditionalfields(List<Map<String, String>> hm, String programId ) {
		String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchNoOfClassesmissingadditonalfield"));
		System.out.println(hm.get(0).get("BatchStatusmissingadditonalfield"));

		//bp.setBatchDescription(hm.get(0).get("BatchDescriptionmissingadditionalfield"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClassesmissingadditonalfield"));
		bp.setBatchStatus(hm.get(0).get("BatchStatusmissingadditonalfield"));
		bp.setProgramId(programId);
		return bp;
		
	}
	public BatchPayload createBatchRequestwithinactiveprogramid(List<Map<String, String>> hm, String programId ) {
		String BatchName="SDET"+getRandomNumber();
		//System.out.println(hm.get(0).get("BatchDescription"));
		//System.out.println(hm.get(0).get("BatchNoOfClasses"));
		//System.out.println(hm.get(0).get("BatchStatus"));


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId("000");
		return bp;
		
	}
	public BatchPayload UpdateBatchputRequest(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  String BatchName="SDET"+getRandomNumber();
		System.out.println(hm.get(0).get("BatchDescription"));
		System.out.println(hm.get(0).get("BatchNoOfClasses"));
		System.out.println(hm.get(0).get("BatchStatus"));
		System.out.println(BatchName);
		System.out.println(programName);
		System.out.println(batchId);
		System.out.println(programId);


		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId(batchId);
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("updateBatchStatus"));
		bp.setProgramId(programId);
		bp.setProgramName(programName);
		return bp;
	}
}
