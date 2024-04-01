
Feature: All GetAll Request for Program module with valid authentication.
#validrequest
Scenario: User Login with given request.
    Given User Login to LMS Application
    When User enter valid credentials
    Then User receives status code 200 with response body with BearerToken
  
  @get_all_programs
Scenario: Check if Admin able to retrieve all programs with valid Endpoint
	Given Admin creates GET Request with valid Endpoint
	When Admin sends HTTPS Request with endpoint
	Then Admin receives 200 OK Status with response body      

#	@all_programs_Negative_Scenario
#	Scenario: Check if Admin able to retrieve all programs with invalid Endpoint 
#	Given Admin creates GET Request with valid Endpoint 
#	When Admin sends HTTPS Request with invalid endpoint
#	Then Admin receives 404 not found  Status with error message 
	
#	@all_programs_invalidmethod_Negative_Scenario
#	 Scenario: Check if Admin able to retrieve all programs with invalid Method 
#	 Given Admin creates GET Request with valid Endpoint
#	 When Admin sends HTTPS Request with invalid method
#	 Then Admin receives 405 not found  Status with error message 

#@getallprogramswithoutauthorization
#Scenario: Check if Admin able to retrieve a program without Authorization
#Given Admin creates GET Request with valid Endpoint 
#When Admin sends HTTPS Request with endpoint without authorization
#Then Admin receives 401 Status with response body as Unauthorized 

@getrequestbyprogramid 
Scenario: Check if Admin able to retrieve a program with valid program ID
Given Admin creates GET Request with valid Endpoint                             
When Admin sends HTTPS Request with a specific programid endpoint
Then Admin receives 200 OK Status with response body

#@getrequestbyinvalidprogramid 
#Scenario: Check if Admin able to retrieve a program with invalid program ID
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with a specific programid endpoint 
#Then Admin receives 404 Not Found Status with error message

#@getrequestbyinvalidbaseURI
#Scenario: Check if Admin able to retrieve a program with invalid baseURI
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with invalid baseURI
#Then Admin receives 404 not found  Status with error message 

#@getrequestbyprogramidwithoutauthorization 
Scenario: Check if Admin able to retrieve a program without Authorization
#Given Admin creates GET Request with valid Endpoint 
#When Admin sends HTTPS Request with endpoint without authorization
#Then Admin receives 401 Status with response body as Unauthorized 

#@getallprogramswithadmins
#Scenario: Check if Admin able to retrieve all programs with Admins valid Endpoint
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with getallprogramswith admins endpoint
#Then Admin receives 200 OK Status with response body
#
#@getallprogramswithadminswithinvalidendpoint
#Scenario: Check if Admin able to retrieve all programs with Admins valid invalidEndpoint
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with invalid endpoint
#Then  Admin receives 404 Not Found Status with error message
#
#@getallprogramswithadminswithinvalidmethod
#Scenario: Check if Admin able to retrieve all programs with invalid Method
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with invalid method
#Then Admin receives 405 not found  Status with error message 
#
#@getallprogramswithadminswithoutAuthorization
#Scenario: Check if Admin able to retrieve all programs without Authorization
#Given Admin creates GET Request with valid Endpoint
#When Admin sends HTTPS Request with endpoint without authorization
#Then Admin receives 401 Status with response body as Unauthorized
#


	                                             