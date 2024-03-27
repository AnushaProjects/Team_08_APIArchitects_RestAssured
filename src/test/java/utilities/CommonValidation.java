package utilities;

import io.restassured.response.Response;

public class CommonValidation {
	
	public void Success(Response res) {
	int Success=res.getStatusCode();
	System.out.println(Success);
}
}
