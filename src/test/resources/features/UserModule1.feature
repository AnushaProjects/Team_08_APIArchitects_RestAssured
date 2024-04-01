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
   