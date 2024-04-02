package utilities_team08;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ReusableVariables {
	
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	
	public String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	
	
	//User Endpoints
	public String path="src/test/resources/TestData/Team_08_API Architects_LMSTestData.xlsx";
	public String user_post_endpoint="/users/roleStatus";
	public String getroles="/users/roles";
	public String allusers="/users/";
	public String allactive="/users/activeUsers";
	public String count_active_inactive="/users/byStatus";
	public String count_by_id="/users/byStatus?id=";
	public String userbatch="/users/programBatch/";
	public String userprogram="/users/programs/";
	public String userrole="/users/roles/";
	public String update_user="/users/"+prop.getProperty("user_id_with_All_field");
	public String update_user_invalid="/users/"+prop.getProperty("negative_scenerio_user_Id");
	public String assign_pb="/users/roleProgramBatchStatus/"+prop.getProperty("user_id_with_All_field");
	public String delete_userid="/userRoleProgramBatchMap/deleteAll/U1808";
	
	
	//ProgramEndpoints
	public String create_program="/saveprogram";
	public String update_program_by_id="/putprogram/"+prop.getProperty("program_id");
	public String negative_update_program_by_id="/putprogram/"+prop.getProperty("negative_scenerio_program_id");
	public String update_program_by_name="/program/"+prop.getProperty("program_name");
	public String get_programs="/allPrograms";
	public String get_programs_byid="/programs/"+prop.getProperty("program_id");
	public String delete_by_program_name="/deletebyprogname/"+prop.getProperty("program_name");
	public String delete_by_program_id="/deletebyprogid/"+prop.getProperty("program_id");
	public String logout="/logoutlms";
	
	//BatchEndpoints
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
	
	//UserProgramBatchMAp endpoints
	
	public String userPBM="/userRoleProgramBatchMap";
	public String userBPM_by_id="/userRoleProgramBatchMap/U1864"; // needs to change UserId
	
	
	
	public RequestSpecification auth_req_post=given()
			.header("Content-Type","application/json")
			.header("Authorization","Bearer " + prop.getProperty("bearer"));
	
	public RequestSpecification noauth_req_post=given()
			.header("Content-Type","application/json");
	
	public String invalid_endpoint="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms/log";
	public String invalid_Id="Uabc";
	public String invalid_integer_Id="2";
	

}
