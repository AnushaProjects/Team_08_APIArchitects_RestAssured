Feature: Delete Request for Batch Module

Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with valid credentials
    When Admin Admin calls Post Https method  with valid endpoint
    Then Admin Admin receives 200 created with auto generated token
    
@Delete_Valid_BatchID    
Scenario: Check if admin able to delete a Batch with valid Batch ID
Given Admin creates DELETE Request
When Admin sends HTTPS Request with valid BatchId and endpoint
Then Admin receives 200 Ok status with message

@Delete_Invalid_EndPoint
Scenario: Check if admin able to delete a Batch with invalid endpoint
Given Admin creates DELETE Request
When Admin sends HTTPS Request  with valid BatchId and invalid endpoint
Then Admin receives 404 not found

@Delete_Invalid_BatchID
Scenario: Check if admin able to delete a Batch with invalid Batch ID
Given Admin creates DELETE Request
When Admin sends HTTPS Request with invalid BatchId valid endpoint
Then Admin receives 404 not found

@Delete_Without_Autherization
Scenario: Check if admin able to delete a Batch without authorization
Given Admin creates DELETE Request with unauthorized access 
When Admin sends HTTPS Request with valid BatchId and endpoint for unautherized
Then Admin receives 401 Unauthorized Status     