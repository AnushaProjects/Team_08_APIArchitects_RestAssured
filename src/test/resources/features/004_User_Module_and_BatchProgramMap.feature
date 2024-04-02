Feature: User Module

    Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives 200 created with auto generated token
    
   @CreatingUserIdWithMandatoryFields
	Scenario: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields
    Given Admin creates POST request with all mandatory fields 
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body. 
  
    @CreatingUserIdWithExistingValues
	Scenario Outline: Check if admin is able to create a Admin with valid endpoint with Existing EmailId and PhoneNumber
    Given Admin creates POST request with all mandatory fields with ExistingValues
    When Admin sends HTTPS Request with endpoint to save "<ExistingValues>"
    Then Admin receives 400 Created Status with response body for "<ExistingValues>"
    
     Examples:
    |ExistingValues|
    |Create|  
    |ExistingPhoneumber|
    |ExistingEmailId|
 
	@CreatingUserIdWithAllFields
	Scenario Outline: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields and Additional fields
    Given Admin creates POST request with all mandatory fields and additional fields
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body and Save userId for "<Scenario>".   
    
    Examples:
    |Scenario|
    |Positive|  
    |Negative|   
  
    @CreatingUserIdWithNoRequestBody
	Scenario: Check if admin is able to create a new Admin without request body
    Given Admin creates POST request with all mandatory fields and additional fields
    When Admin sends HTTPS Request with endpoint without Requestbody
    Then Admin receives 400 Bad Request Status with message and boolean success details   
     
   
    @CreatingUserIdWithInvalidValues
	Scenario Outline: Check if admin is able to create a Admin with valid endpoint and invalid values in request body
    Given Admin creates POST request with all mandatory fields and additional "fieldsInvalid" "<InvalidValue>"
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details
    
     Examples:
    |InvalidValue|
    |Firstname|  
    |TimeZone|
    |LinkedIn|
    |VisaStatus|
    |PhoneNumber|
    |LoginEmail|
    |EduUg|
    |EduPg|
    
    @CreatingUserIdWithMissingFields
	Scenario Outline: Check if admin is able to create a Admin with valid endpoint and missing fields in request body
    Given Admin creates POST request with all mandatory fields and additional "fieldsmissing" "<missing>"
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details
    
     Examples:
    |missing|
    |Firstname|  
    |TimeZone|
    |LinkedIn|
    |VisaStatus|
    |PhoneNumber|
    |LoginEmail|
    |EduUg|
    |EduPg|
                     
    	@CreatingUserUnauthorized
	Scenario: Check if admin able to create a new Admin with request body without authorization
    Given Admin creates POST request with all mandatory fields and additional fields
    When Admin sends HTTPS Request with endpoint Unauthorized
    Then Admin receives status 401 with Unauthorized message                                                
 
    
  @GetAllRoles
  Scenario: Check if admin is able to retreive all the available roles with Authorization
    Given Admin creates GET Request
    When Admin sends HTTPS Request with GET All Roles endpoint
    Then Admin receives 200 OK
    
     @GetAllRolesUnauthorized
  Scenario: Check if admin is able to retreive all the available roles without Authorization
    Given Admin creates GET Request
    When Admin sends HTTPS Request with GET Unauthorized "GetAllRoles"
    Then Admin receives status 401 with message
    
    @GetAllRolesInvalidEndpoint
  Scenario: Check if admin is able to retreive all the available roles with invalid End point
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message
    
    
 @GetAllAdmins
  Scenario: Check if admin able to retrieve all Admin with valid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with valid endpoint for AllAdmins
    Then Admin receives 200 OK Status with response body "AllAdmins"

@GetAllAdminsUnauthorized
  Scenario: Check if admin is able to retreive all the available Admin without Authorization
    Given Admin creates GET Request
    When Admin sends HTTPS Request with GET Unauthorized "GetAllAdmins"
    Then Admin receives status 401 with message
    
    @GetAllAdminsInvalidEndpoint
  Scenario: Check if admin is able to retreive all the available Admin with invalid End point
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message
    
    @GetAdminById
  Scenario: Check if admin able to retrieve a Admin with valid Admin ID
    Given Admin creates GET Request with "valid" AdminId
    When Admin sends HTTPS Request with valid endpoint for AdminByID
    Then Admin receives 200 OK Status with response body "AdminByID"
    
     @GetAdminByIdInvalidEndpoint
  Scenario: Check if admin able to retrieve a Admin with valid Admin ID and invalid endpoint
    Given Admin creates GET Request with "valid" AdminId
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message
    
    @GetAdminbyIdUnauthorized
  Scenario: Check if admin is able to retreive Admin with valid Admin ID without Authorization
    Given Admin creates GET Request with "valid" AdminId
    When Admin sends HTTPS Request with GET Unauthorized "GetAdminbyId"
    Then Admin receives status 401 with message
    
     @GetAdminByInvalidUserId
  Scenario: Check if admin able to retrieve a Admin with Invalid Admin ID
    Given Admin creates GET Request with "Invalid" AdminId
    When Admin sends HTTPS Request with valid endpoint for AdminByID
    Then Admin receives status 404 with message
    
    @GetAllActiveAdmins
  Scenario: Check if admin able to retrieve all active Admins
    Given Admin creates GET Request 
    When Admin sends HTTPS Request with valid endpoint for ActiveAdmin
    Then Admin receives 200 OK Status with response body "AllActiveAdmins"
   
    
    @GetAllActiveAdminsUnauthorized
  Scenario: Check if admin is able to retreive all active Admins without Authorization
    Given Admin creates GET Request
    When Admin sends HTTPS Request with GET Unauthorized "GetAllActiveAdmin"
    Then Admin receives status 401 with message
                                    
                                     
    @GetAllActiveAdminsInvalidEndpoint
  Scenario: Check if admin able to retrieve all active Admins with invalid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message                               
    
    @GetCountActiveInactive
  	Scenario: Check if admin is able to get count of active and inactive Admins
    Given Admin creates GET Request
    When Admin sends HTTPS Request with valid endpoint for ActiveInActive
    Then Admin receives 200 OK Status with response body "CountActiveInActive"
    
    @GetCountActiveInactiveUnauthorized
  	Scenario: Check if admin is able to get count of active and inactive Admins without Authorization
    Given Admin creates GET Request
    When Admin sends HTTPS Request with GET Unauthorized "CountActiveInActive"
    Then Admin receives status 401 with message
    
    @GetCountActiveInactiveInvalidEndpoint
  Scenario: Check if admin able to get count of active and inactive Admins with invalid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message 
    
     @GetCountActiveInactivebyRoleId
  Scenario Outline: Check if admin is able to get count of active and inactive Admins
    Given Admin creates GET Request with "<roleId>" 
    When Admin sends HTTPS Request with valid endpoint with "RoleId"
    Then Admin receives 200 OK Status with response body "CountActiveInActivebyRole"
    
    Examples:
    |roleId|
    |R01|  
    |R02|   
    |R03|  
           
    @GetCountActiveInactivebyInvalidRoleId
  Scenario: Check if admin is able to get count of active and inactive Admins by invalid role ID 
    Given Admin creates GET Request with "InvalidroleId" scenario
    When Admin sends HTTPS Request with valid endpoint with "InvalidRoleId"
    Then Admin receives status 404 with message      
    
           
    
    
    
 		@GetAdminsbyRole
     Scenario Outline: Check if admin is able to retreive Admins by valid role ID
    Given Admin creates GET Request with "valid" Role Id
    When Admin sends HTTPS Request with valid endpoint for AdminsbyRoleId
    Then Admin receives 200 OK Status with response body "Role" 
    
      @GetAdminsbyInvalidRoleId
  Scenario Outline: Check if admin is able to retreive Admins by Invalid role ID
    Given Admin creates GET Request with "invalid" Role Id
    When Admin sends HTTPS Request with valid endpoint with "InvalidRoleIds"
    Then Admin receives status 404 with message 
    
     @GetAdminsbyRoleIdUnauthorized
  	Scenario: heck if admin is able to retreive Admins by valid role ID without Authorization
    Given Admin creates GET Request with "valid" Role Id
    When Admin sends HTTPS Request with GET Unauthorized "UserbyRoleId"
    Then Admin receives status 401 with message
    
    @GetAdminsbyRoleIdInvalidEndpoint
  Scenario: Check if admin able to get the Admins by role with invalid endpoint
    Given Admin creates GET Request with "valid" Role Id
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message 
    
    # Comment
    
    
  
   
   #UmaRani
    #GET Request getAllUsersWithRoles POSITIVE
  Scenario: Check if admin is able to retreive all the available roles with Authorization
    Given User creates getAllUsersWithRoles request for the LMS API endpoint with "valid credentials"
    When User  sends HTTPS Request with "valid endpoint"
    Then Admin receives 200 OK Status with response body for getAllUsersWithRoles

  #REQUEST_(Admin with filters)
  @GetReqWithAdminfilters
  Scenario: Check if admin is able to retrieve all Admins with filters
    Given Admin creates GET Adminfilters Request
    When Admin sends HTTPS Request with Admins with filters "valid endpoint"
    Then Admin receives 200 OK Status with response body for GetReqWithAdminfilters

  #PUT Request (Update Admin Role ID) POSITIVE
  Scenario: Check if admin is able to update role id of a Admin by valid Admin id
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with Update Admin Role ID endpoint
    Then Admin receives 200 OK Status with Update Admin Role ID response body

  #PUT Request (Update User Role Status) POSITIVE
  Scenario: Check if admin is able to update role status of a Admin with valid Admin id
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with Update User Role Status endpoint
    Then Admin receives 200 OK Status with "PUT - Update Admin role status by Admin ID" response body

  #PUT REQUEST (Update Admin Role Program Batch status) POSITIVE - Needs program id /batch id from swati and priyanka code
  Scenario: Check if admin is able to assign Admin to with program/batch by Admin Id
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS Request with Update Admin Role Program Batch status endpoint
    Then Admin receives 200 OK Status with "PUT - Update Admin Role Program Batch status" response body
    
     @GetAdminsbyProgramBatches 
     Scenario Outline: Check if admin is able to get the Admins by program batches for valid batch ID
    Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches 
    Then Admin receives 200 OK Status with response body "ProgramBatches"   
    
     @GetAdminsbyInvalidBatchId
  Scenario: Check if admin is able to get the Admins by program batches for invalid batch ID
    Given Admin creates GET Request with "InvalidbatchId" scenario
    When Admin sends HTTPS Request with valid endpoint with "InvalidBatchId"
    Then Admin receives status 404 with message 
    
     @GetAdminsbyProgramBatchesUnauthorized
  	Scenario: Check if admin is able to get the Admins by program batches for valid batch ID without Authorization
   Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with GET Unauthorized "UserbyBatch"
    Then Admin receives status 401 with message
    
    @GetAdminsbyProgramBatchesEndpoint
  Scenario: Check if admin able to get the Admins by program batches with invalid endpoint
  Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message 
    
    
     @GetAdminsbyProgram
     Scenario Outline: Check if admin is able to get the Admins for valid program Id
    Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches program
    Then Admin receives 200 OK Status with response body "Program"  
    
     @GetAdminsbyInvalidProgramId
  Scenario: Check if admin is able to get the Admins by program batches for invalid batch ID
    Given Admin creates GET Request with "invalid" programbatch Id
    When Admin sends HTTPS Request with valid endpoint with "InvalidProgramId"
    Then Admin receives status 404 with message 
    
     @GetAdminsbyProgramUnauthorized
  	Scenario: Check if admin is able to get the Admins by program for valid program ID without Authorization
    Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with GET Unauthorized "UserbyProgram"
    Then Admin receives status 401 with message
    
    @GetAdminsbyProgramInvalidEndpoint
  Scenario: Check if admin able to get the Admins by program batches with invalid endpoint
    Given Admin creates GET Request with "valid" programbatch Id
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives status 404 with message 

  #PUT REQUEST (Update Admin login status) POSITIVE
  Scenario: Check if admin is able to update the Admin login status by Admin ID
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with Update Admin login status endpoint
    Then Admin receives 200 OK Status with "Update Admin login status" response body

  #PUT Request (Update Admin info) POSITIVE
  Scenario: Check if admin is able to update Admin details with Admin id and valid data in all fields
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS request with put endpoint
    Then Admin receives 200 OK Status with "PUT - User" response body

  #Delete Request POSITIVE
  Scenario: Check if Admin able to delete a Admin with valid Admin Id
    When Admin sends HTTPS request with delete endpoiint
    Then Admin receives 200 OK Status with "response message" for delete user request

  #PUT Request - (Update Admin Role ID) NEGATIVE SCENARIOS
  Scenario: Check if admin is able to update a Admin with Admin Role Id with already existing Admin role ID
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body with existing id
    When Admin sends HTTPS Request with Update Admin Role ID endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details for Update Admin Role ID

  Scenario: Check if admin is able to update role id of a Admin by valid Admin id
    Given Admin creates Update Admin Role ID PUT Request with invalid Update Admin Role ID request body
    When Admin sends HTTPS Request with Update Admin Role ID endpoint

  #PUT Request (Update User Role Status) NEGATIVE
  Scenario: Check if admin is able to update role status of a Admin with valid Admin id and invalid role status
    Given Admin creates Update User Role Status PUT Request with in valid role status in request body
    When Admin sends HTTPS Request with Update User Role Status endpoint

   #All PUT Requests Invalid AdminID scenario
  Scenario: Check if admin is able to update role id of a Admin with invalid Admin id
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role ID" endpoint
    Then Admin receives 404 Bad Request Status with message and boolean success details for update user

  Scenario: Check if admin is able to update role status of a Admin with invalid Admin id
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin role status by Admin ID" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role Program Batch status" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin login status" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - User" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    When Admin sends HTTPS Request with invalid AdminId and valid "DELETE - User" endpoint

  #All PUT Requests Invalid endpoint scenario
  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin Role ID" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin role status by Admin ID" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin Role Program Batch status" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin login status" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - User" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    When Admin sends HTTPS Request with invalid "DELETE - User" endpoint

  #All PUT Requests with No Authorization
  Scenario: Check if admin is able to update role id of a Admin by valid Admin id with no authorization
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS "PUT - Update Admin Role ID" Request with endpoint unauthorized
    Then Admin receives status 401 with Unauthorized Message

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin role status by Admin ID" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin Role Program Batch status" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin login status" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - User" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    When Admin sends HTTPS "DELETE - User" Request with endpoint unauthorized
    
   @GetAllUserProgramBatch
Scenario: Check if admin is able to retreive all Admins with assigned program batches
Given Admin  creates GET Request to retrieve all Admins assigned to programsbatches 
When Admin  sends HTTPS Request 
Then Admin  receives 200 OK

@GetAllUserProgramBatchNOAuth
Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin  creates GET Request to retrieve all Admins assigned to programsbatches 
When Admin sends HTTPS Request  with NoAuth
Then Admin  receives status 401 with Unauthorized message


@GetAllUserProgramBatchByValidAdmin
Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "valid" AdminID 
When Admin sends HTTPS Request 
Then Admin  receives 200 OK


@GetAllUserProgramBatchByInvalidAdmin
Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "invalid" AdminID 
When Admin sends HTTPS Request 
Then Admin receives 404 Not Found

@GetAllUserProgramBatchByAdminNOAuth
Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "valid" AdminID
When Admin sends HTTPS Request  with NoAuthAdmin
Then Admin  receives status 401 with Unauthorized message 

#Delete Request POSITIVE
  Scenario: Check if Admin able to delete a Admin with valid Admin Id
    When Admin sends HTTPS request with delete endpoiint
    Then Admin receives 200 OK Status with "response message" for delete user request
  
   #@Delete_ValidName_Valid_EndPoint_Id  
 #Scenario: Check if Admin able to delete a program with valid userId 
 #Given Admin creates Delete Request for userId
 #When Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point userId
 #Then Admin receives 200 Ok status with message for Delete userId