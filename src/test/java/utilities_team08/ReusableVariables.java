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
	
	//ProgramEndpoints
	public String create_program="/saveprogram";
	public String update_program_by_id="/putprogram/"+prop.getProperty("program_id");
	public String negative_update_program_by_id="/putprogram/"+prop.getProperty("negative_scenerio_program_id");
	public String update_program_by_name="/program/"+prop.getProperty("program_name");
	public String get_programs="/allPrograms";
	public String get_programs_byid="/programs/"+prop.getProperty("program_id");
	
	//UserProgramBatchMAp endpoints
	
	public String userPBM="/userRoleProgramBatchMap";
	public String userBPM_by_id="/userRoleProgramBatchMap/U01"; // needs to change UserId
	
	
	
	public RequestSpecification auth_req_post=given()
			.header("Content-Type","application/json")
			.header("Authorization","Bearer " + prop.getProperty("bearer"));
	
	public RequestSpecification noauth_req_post=given()
			.header("Content-Type","application/json");
	
	public String invalid_endpoint="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms/log";
	public String invalid_Id="Uabc";
	public String invalid_integer_Id="2";
	

}
