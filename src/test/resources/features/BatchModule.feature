Feature: Create batch program
Scenario: User is logged in
Given Admin creates request with valid credentials
When Admin Admin calls Post Https method  with valid endpoint
Then Admin Admin receives 200 created with auto generated token

Scenario: Creating a program
Given Admin creates POST program Request  with valid data in request body 
When Admin sends HTTPS Request with program endpoint 
 Then Admin receives 201 Created Status with response body in program.                                                          


Scenario: Check if admin able to create a Batch with valid endpoint and request body without authorization.
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with endpoint without authorization
 Then Admin receives 401 Unauthorized
  
 Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with endpoint 
 Then Admin receives 201 Created Status with response body.                                                          
 
 Scenario: Check if admin able to create a Batch with valid endpoint and request body (existing value in Batch Name)
 Given Admin creates POST batch Request  with existing value in request body 
 When Admin sends HTTPS batch Request with endpoint with existing value in batchname
 Then Admin receives 400 Bad Request Status with message and boolean success details
 
 Scenario: Check if admin able to create a Batch missing mandatory fields in request body
 Given Admin creates POST batch Request  with invalid data in request body 
 When Admin sends HTTPS batch Request with endpoint with missing mandatory fields
 Then Admin receives 400 Bad Request Status with message and boolean success details
 
 Scenario: Check if admin able to create a batch with invalid endpoint
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with invalid endpoint 
 Then Admin receives 404 not found  Status 
 
 Scenario: Check if admin able to create a batch with missing additional fields
 Given Admin creates POST batch Request with missing additional fields
 When Admin sends HTTPS batch Request with endpoint 
 Then Admin receives 201 Created Status with response body for missing additional fields.                                                          
 
 Scenario: Check if admin able to create a batch with invalid data in request body
 Given Admin creates POST batch Request  with invalid data in request body
 When Admin sends HTTPS batch Request with endpoint and invalid data
 Then Admin receives 400 Bad Request Status with message and boolean success details
 
 Scenario: Check if admin able to create a batch  with inactive program ID
 Given Admin creates POST batch Request with inactive program id
 When Admin sends HTTPS batch Request with endpoint and invalid data
 Then Admin receives 400 Bad Request Status with message and boolean success details
 
 #Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
 #Given Admin creates PUT batch Request with valid BatchId and Data
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 401 unauthorized
 #
 #Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
 #Given Admin creates PUT batch Request with valid BatchId and Data
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 200 OK Status with updated value in response body.                                         
 #
 #Scenario: Check if admin able to update a Batch with invalid batchID and mandatory fields in request body
 #Given Admin creates PUT batch Request with invalid BatchId and valid Data
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 404 Not Found Status with message and boolean success details
 #
 #Scenario: Check if admin able to update a Batch with valid batchID and missing mandatory fields request body
 #Given Admin creates PUT batch Request with valid batch Id and missing mandatory fields
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to update a batch with invalid data
 #Given Admin creates PUT batch Request with invalida data
 #When Admin creates PUT batch Request with invalida data
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to update a Batch with invalid enpoint
 #Given Admin creates PUT batch Request with Valid batch Id
 #When Admin sends HTTPS batch Request with invalid endpoint
 #Then Admin receives 404 not found
 #
 #Scenario: Check if admin able to update a Batch with a valid batchID and deleted programID field  in the request body with other mandatory fields
 #Given Admin creates PUT batch Request with Valid batch Id
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 400 Bad Request Status with message and boolean success details
 #
 #Scenario: Check if admin able to update a Batch with a deleted batchID in the endpoint
 #Given Admin creates PUT batch Request with deleted batch Id
 #When Admin sends HTTPS batch Request with endpoint
 #Then Admin receives 200 Ok status with message
 #
 #
 #
 #
 #
 
 
 
 
 
 
 
 