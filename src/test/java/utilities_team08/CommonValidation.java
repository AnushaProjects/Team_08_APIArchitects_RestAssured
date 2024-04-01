package utilities_team08;

import org.junit.Assert;

import io.restassured.response.Response;

public class CommonValidation {
	
	public void Success(Response res) {
	int Success=res.getStatusCode();
	System.out.println(Success);
}
	public void statusValidations(Response response,int statuscode) {
		System.out.println(response.getStatusCode());
		int codeofstatus=response.getStatusCode();
		Assert.assertEquals(codeofstatus,statuscode);
		
		
	}
	public void messageValidations(Response response,boolean successvalue) {
		//response.then().assertThat().body("success", Matchers.equalTo(successvalue));
		System.out.println(response.jsonPath().get("success"));
		Assert.assertSame(response.jsonPath().get("success"),successvalue);
       String responsemessage=(response.path("message"));
       responsemessage.isEmpty();
     Assert.assertFalse(responsemessage.isEmpty());

	}
	public void headervalidations(Response response) {
		
		
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertEquals(response.getHeader("Server"), "Cowboy");
		Assert.assertEquals(response.getHeader("transfer-encoding"), "chunked");
		
	}
}
