
Feature: Creating a program Using post request

@validrequest
Scenario: User Login with given request.
    Given User Login to LMS Application
    When User enter valid credentials
    Then User receives status code 200 with response body with BearerToken
  
 
 	Scenario: Creating a program
Given Admin creates POST program Request with valid data in request body
When Admin sends HTTPS Request with program endpoint
Then Admin receives 201 Created Status with response body.
#
 #Scenario: Check if Admin able to create a program with valid endpoint and request body without Authorization
#Given Admin creates POST program Request with valid data in request body
#When Admin sends HTTPS Request and request Body with endpoint without authorization
#Then Admin receives 401 Unauthorized

#Scenario: Check if Admin able to create a program with invalid endpoint
#Given Admin creates POST program Request with valid data in request body
#When Admin sends HTTPS Request and request Body with invalid endpoint
#Then Admin receives 404 Bad Request Status 

#@invalidrequest
#Scenario: Check if Admin able to create a program with invalid request body
#Given Admin creates POST Request for the LMS with invalid request body
#When Admin sends HTTPS Request and invalid request Body with endpoint
#Then Admin receives 400 Bad Request Status
@missingvaluesinrequestbody
Scenario: Check if Admin able to create a program with missing values in the request body
Given Admin creates POST program Request with invalid valid data in request body
When Admin sends HTTPS Request and  missingvaluesinrequest Body with endpoint
Then Admin receives 400 Bad Request Status 
#@missingadditionalfields
#Scenario: Check if Admin able to create a program with missing additional fields in the request body
#Given Admin creates POST program Request with valid data in request body
#When Admin sends HTTPS Request and missing additional fields with endpoint
#Then Admin receives 200 ok