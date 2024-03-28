Feature: Create batch program
Background: User is logged in

Given Admin creates request with valid credentials
    When Admin Admin calls Post Https method  with valid endpoint
    Then Admin Admin receives 200 created with auto generated token

Scenario: Creating a program
Given Admin creates POST program Request  with valid data in request body 
When Admin sends HTTPS Request with program endpoint 
 Then Admin receives 201 Created Status with response body.                                                          

 #Scenario: Check if admin able to create a Batch 
 #with valid endpoint and request body without authorization.
 #Given Admin creates POST Request  with valid data in request body 
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 401 Unauthorized
 #
 #Scenario: Check if admin able to create a Batch with 
 #valid endpoint and request body (non existing values)
 #Given Admin creates POST Request  with valid data in request body 
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 201 Created Status with response body.                                                          
 #
 #Scenario: Check if admin able to create a Batch with valid endpoint and request body (existing value in Batch Name)
 #Given Admin creates POST Request  with existing value in request body 
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to create a Batch missing mandatory fields in request body
 #Given Admin creates POST Request  with invalid data in request body 
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to create a batch with invalid endpoint
 #Given Admin creates POST Request 
 #When Admin sends HTTPS Request with invalid endpoint 
 #Then Admin receives 404 not found  Status 
 #
 #Scenario: Check if admin able to create a batch with missing additional fields
 #Given Admin creates POST Request with missing additional fields
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 201 Created Status with response body.                                                          
 #
 #Scenario: Check if admin able to create a batch with invalid data in request body
 #Given Admin creates POST Request  with invalid data in request body
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to create a batch  with inactive program ID
 #Given Admin creates POST Request with inactive program id
 #When Admin sends HTTPS Request with endpoint 
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 