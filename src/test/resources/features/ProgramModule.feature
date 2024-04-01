Feature: Create Program

Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives 200 created with auto generated token

Scenario Outline: Creating a program
Given Admin creates POST program Request  with valid data in request body 
When Admin sends HTTPS Request with program endpoint 
Then Admin receives 201 Created Status with response body in program "<Scenario>"

 Examples:
    |Scenario|
    |Positive|  
    |Negative| 

#Scenario: Check if admin able to create a Batch with valid endpoint and request body without authorization.
 #Given Admin creates POST batch Request  with valid data in request body 
 #When Admin sends HTTPS batch Request with endpoint without authorization
 #Then Admin receives 401 Unauthorized