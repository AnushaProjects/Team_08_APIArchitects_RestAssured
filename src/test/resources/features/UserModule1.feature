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
    
    
    
 @GetAllAdmins
  Scenario: Check if admin able to retrieve all Admin with valid endpoint
    Given Admin creates GET Request
    When Admin sends HTTPS Request with valid endpoint for AllAdmins
    Then Admin receives 200 OK Status with response body
    
   
    @GetAdminById
  Scenario: Check if admin able to retrieve a Admin with valid Admin ID
    Given Admin creates GET Request with valid AdminId
    When Admin sends HTTPS Request with valid endpoint for AdminByID
    Then Admin receives 200 OK Status with response body  
    
   
    @GetAllActiveAdmins
  Scenario: Check if admin able to retrieve all active Admins
    Given Admin creates GET Request 
    When Admin sends HTTPS Request with valid endpoint for ActiveAdmin
    Then Admin receives 200 OK Status with response body 
                                                                   
    
    @GetCountActiveInactive
  Scenario: Check if admin is able to get count of active and inactive Admins
    Given Admin creates GET Request
    When Admin sends HTTPS Request with valid endpoint for ActiveInActive
    Then Admin receives 200 OK Status with response body 
    
     @GetCountActiveInactivebyRoleId
  Scenario Outline: Check if admin is able to get count of active and inactive Admins
    Given Admin creates GET Request with "<roleId>"
    When Admin sends HTTPS Request with valid endpoint for ActiveInActive with RoleId
    Then Admin receives 200 OK Status with response body
    
    Examples:
    |roleId|
    |R01|  
    |R02|   
    |R03|  
            
     @GetAdminsbyProgramBatches 
     Scenario Outline: Check if admin is able to get the Admins by program batches for valid batch ID
    Given Admin creates GET Request with valid programbatch Id
    When Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches 
    Then Admin receives 200 OK Status with response body    
    
     @GetAdminsbyProgram
     Scenario Outline: Check if admin is able to get the Admins for valid program Id
    Given Admin creates GET Request with valid programbatch Id
    When Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches program
    Then Admin receives 200 OK Status with response body  
    
 		@GetAdminsbyRole
     Scenario Outline: Check if admin is able to retreive Admins by valid role ID
    Given Admin creates GET Request with valid role ID 
    When Admin sends HTTPS Request with valid endpoint for AdminsbyRoleId
    Then Admin receives 200 OK Status with response body 
   