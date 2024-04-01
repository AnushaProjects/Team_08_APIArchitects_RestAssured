package request_body_raw_team08;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.PPMT;
import org.json.JSONObject;

import payload_team08.BatchPayload;
import payload_team08.ProgramPayload;
import utilities_team08.ReusableMethods;

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

		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClassesmissingadditonalfield"));
		bp.setBatchStatus(hm.get(0).get("BatchStatusmissingadditonalfield"));
		bp.setProgramId(programId);
		return bp;
		
	}
	public BatchPayload createBatchRequestwithinactiveprogramid(List<Map<String, String>> hm, String programId ) {
		String BatchName="SDET"+getRandomNumber();
		
		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId("000");
		return bp;
		
	}
	public BatchPayload UpdateBatchputRequest(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  String BatchName="SDET"+getRandomNumber();



		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId(batchId);
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		bp.setProgramName(programName);
		return bp;
	}
	public BatchPayload UpdateBatchputRequest_invalidbatch_id(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  String BatchName="SDET"+getRandomNumber();
		
		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId("123");
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		bp.setProgramName(programName);
		return bp;
	}
	public BatchPayload UpdateBatchputRequest_withmissingdata_validbatch_id(List<Map<String, String>> hm) {
		BatchPayload bpUpdateBatchIdPayLoad = new BatchPayload();
		bpUpdateBatchIdPayLoad.setBatchDescription("");
		bpUpdateBatchIdPayLoad.setBatchId(hm.get(0).get("updatebatchid"));
		bpUpdateBatchIdPayLoad.setBatchName("");
		bpUpdateBatchIdPayLoad.setBatchNoOfClasses("");
		bpUpdateBatchIdPayLoad.setBatchStatus("");
		bpUpdateBatchIdPayLoad.setProgramName("");
		bpUpdateBatchIdPayLoad.setProgramId("");
		return bpUpdateBatchIdPayLoad;
	}
	public BatchPayload UpdateBatchputRequestwith_invalid_data(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  
        bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId("0000$");
		bp.setBatchName("0000#");
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId("0000*");
		bp.setProgramName("0000*");
		return bp;
	}
	
	public BatchPayload UpdateBatchputRequest_deleted_programidfield(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  String BatchName="SDET"+getRandomNumber();



		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId(batchId);
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramName(programName);
		return bp;
	}
	public BatchPayload UpdateBatchputRequest_with_deleted_batchid(List<Map<String, String>> hm, String programId,String batchId,String programName ) {
		  String BatchName="SDET"+getRandomNumber();



		bp.setBatchDescription(hm.get(0).get("BatchDescription"));
		bp.setBatchId(hm.get(0).get("deletedbatchid"));
		bp.setBatchName(BatchName);
		bp.setBatchNoOfClasses(hm.get(0).get("BatchNoOfClasses"));
		bp.setBatchStatus(hm.get(0).get("BatchStatus"));
		bp.setProgramId(programId);
		bp.setProgramName(programName);
		return bp;
	}
	

	
}
