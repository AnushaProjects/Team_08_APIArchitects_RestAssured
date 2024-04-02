Feature: Program Module

Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives 200 created with auto generated token

@CreateProgramId
Scenario Outline: Creating a program
Given Admin creates POST program Request  with valid data in request body 
When Admin sends HTTPS Request and  request Body with "valid" endpoint
Then Admin receives 201 Created Status with response body in program "<Scenario>"

 Examples:
    |Scenario|
    |Positive|  
    |Negative| 
    |Positive2|

@CreateProgramIdUnauthorized
Scenario: Creating a program without Autorisation
 Given Admin creates POST program Request  with valid data in request body
 When Admin sends HTTPS batch Request with endpoint without authorization
 Then Admin receives 401 Unauthorized
 
 @CreateProgramIdInvalidEndpoint
 Scenario: Check if Admin able to create a program with invalid endpoint
 Given Admin creates POST program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with "invalid" endpoint
 Then Admin receives 404 not found  Status with message and boolean success details
 
 @CreateProgramIdNoRequestBody
  Scenario: Check if Admin able to create a program with no bodyrequest
 Given Admin creates POST program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with no bodyrequest
 Then Admin receives 400 not found  Status with message and boolean success details
 
 @CreateProgramIdInvalidMethod
  Scenario: Check if Admin able to create a program with invalid method
 Given Admin creates POST program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with wrongMethod
 Then Admin receives 405 not found  Status with message and boolean success details
 
 
   @CreatingProgrambyIdwithInvalidMissing
	Scenario Outline: Check if Admin able to update a program with valid programID endpoint  and valid request body
    Given Check if Admin able to create a program with invalid request body "<InvalidValue>"
    When Admin sends HTTPS Request and  request Body with "valid" endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success programdetails
    
     Examples:
    |InvalidValue|
    |Program Description|  
    |Program Name|
    |Program Status|
    
    @UpdateProgram
	Scenario Outline: Updating a program
	Given Check if Admin able to "<updateby>" a program with valid programID endpoint  and valid request body
	When Admin sends HTTPS Request and  request Body with "valid" endpoint for PUT "<updateby>"
	Then Admin receives 200 OK Status with updated value in response body. 
	
		Examples:     
		|updateby|
    |ProgramName|  
    |ProgramId| 
    
    @UpdateProgramIdInvalidEndpoint
 Scenario: Check if Admin able to Update a program with invalid endpoint
 Given Admin creates PUT program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with "invalid" endpoint put
 Then Admin receives 404 not found  Status with message and boolean success details
 
  @UpdateProgramIdNoRequestBody
  Scenario: Check if Admin able to Update a program with no bodyrequest
 Given Admin creates PUT program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with no bodyrequest put
 Then Admin receives 400 not found  Status with message and boolean success details
 
 @UpdateProgramIdInvalidMethod
  Scenario: Check if Admin able to Update a program with invalid method
 Given Admin creates POST program Request  with valid data in request body
 When Admin sends HTTPS Request and  request Body with wrongMethod
 Then Admin receives 405 not found  Status with message and boolean success details
 
 
    @UpdatingProgrambyIdwithInvalidMissing
	Scenario Outline: Check if Admin able to update a program with valid programID endpoint  and valid request body
    Given Check if Admin able to update a program with invalid request body "<InvalidValue>"
    When Admin sends HTTPS Request and  request Body with "valid" endpoint InvalidPUT
    Then Admin receives 400 Bad Request Status with message and boolean success programdetails
    
     Examples:
    |InvalidValue|
    |Program Description|  
    |Program Name|
    |Program Status|
                                    
  @get_all_programs
Scenario: Check if Admin able to retrieve all programs with valid Endpoint
	Given Admin creates GET Request with valid Endpoint get
	When Admin sends  HTTPS Request with "valid" endpoint
	Then Admin  receives 200 OK Status with response body 
	
@all_programs_Invalid_End_point
	Scenario: Check if Admin able to retrieve all programs with invalid Endpoint 
	Given Admin creates GET Request with valid Endpoint get 
	When Admin sends  HTTPS Request with "invalid" endpoint
	Then Admin receives 404 not found  Status with message and boolean success details


 @all_programs_no_auth
Scenario: Check if Admin able to retrieve all programs with No Authorization
 Given Admin creates GET Request with valid Endpoint get 
 When Admin sends HTTPS get Request with endpoint without authorization
 Then Admin receives 401 Unauthorized
 
 
 @getrequestbyprogramid 
Scenario: Check if Admin able to retrieve a program with valid program ID
Given Admin creates GET Request with valid Endpoint                             
When Admin sends HTTPS Request with a specific programid "valid" endpoint
Then Admin  receives 200 OK Status with response body


@all_programs_by_Id_Invalid_End_point
	Scenario: Check if Admin able to retrieve all programs with invalid Endpoint 
	Given Admin creates GET Request with valid Endpoint 
	When Admin sends HTTPS Request with a specific programid "invalid" endpoint
	Then Admin receives 404 not found  Status with message and boolean success details


 @all_programs_by_id_no_auth
Scenario: Check if Admin able to retrieve all programs with No Authorization
 Given Admin creates GET Request with valid Endpoint get 
 When Admin sends HTTPS get Request with endpoint without authorization
 Then Admin receives 401 Unauthorized
 
 #//Delete
 
 @Delete_ValidName_Valid_EndPoint  
 Scenario: Check if Admin able to delete a program with valid programName  
 Given Admin creates Delete Request for Program
 When Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point
 Then Admin receives 200 Ok status with message for Delete
 
 @Delete_Prgram_Invalid_Name
Scenario: Check if Admin able to delete a program with valid LMS API,invalid programName
Given Admin creates Delete Request for Program 
When Admin sends HTTPS Request for Delete with Valid Program Name and Invalid End Point
Then Admin receives 404 not found  Status with message and boolean success details

@Delete_ValidName_Without_Autherization
Scenario: Check if Admin able to delete a Program Name without Authorization
Given Admin creates Delete Request for Program
When Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point no auth
Then Admin receives 401 Unauthorized
 
  @Delete_ValidName_Valid_EndPoint_Id  
 Scenario: Check if Admin able to delete a program with valid programId 
 Given Admin creates Delete Request for Program by Id
 When Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point
 Then Admin receives 200 Ok status with message for Delete
 
 @Delete_Prgram_Invalid_Name
Scenario: Check if Admin able to delete a program with valid LMS API,invalid programName
Given Admin creates Delete Request for Program by Id
When Admin sends HTTPS Request for Delete with Valid Program Id and Invalid End Point
Then Admin receives 404 not found  Status with message and boolean success details

@Delete_ValidName_Without_Autherization
Scenario: Check if Admin able to delete a Program Name without Authorization
Given Admin creates Delete Request for Program by Id
When Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point no auth
Then Admin receives 401 Unauthorized
 
