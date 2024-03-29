Feature: User Module


	Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives 200 created with auto generated token
    
  #@GetRequest_AllRoles
  #Scenario: Check if admin is able to retreive all the available roles with Authorization
    #Given User creates request for the LMS API endpoint with Authorization
    #When User  sends HTTPS Request with GET All Roles endpoint
    #Then User receives status code 200 with response body for viewing an User by Role
    
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