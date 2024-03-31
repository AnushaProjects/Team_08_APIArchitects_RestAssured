Feature: ALl GetAll Request for Batch Module with valid authentication

#Scenario: Check if Admin able to generate token with valid credential
    #Given Admin creates request with valid credentials
    #When Admin Admin calls Post Https method  with valid endpoint
    #Then Admin Admin receives 200 created with auto generated token

@GetAll_Batch
Scenario: Check if admin able to retrieve all batches with valid LMS API
Given Admin creates GET Request
When Admin sends HTTPS Request for GetAll_batch with valid endpoint
Then Admin receives 200 OK Status with response body.

@Batch_With_Invalid_EndPoint
Scenario: Check if admin able to retrieve all batches with invalid Endpoint
Given Admin creates GET Request
When Admin sends HTTPS Request for GetAll_batch with invalid endpoint
Then Admin receives 404 status with error message Not Found.

#@Batch_With_SearchField_ID
#Scenario: Check if admin able to retrieve all batches with search string
#Given Admin creates GET Request
#When Admin sends HTTPS Request with endpoint
#Then Admin receives 200 OK Status with response body.
#
#@Batch_With_Invalid_SearchField_ID
#Scenario: Check if admin able to retrieve all batches with invalid search string
#Given Admin creates GET Request
#When Admin sends HTTPS Request with invalid searchfieldID and valid endpoint
#Then Admin receives 404 status with error message Not Found.

@Get_Valid_BatchID
Scenario: Check if admin able to retrieve a batch with valid BATCH ID
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for BatchID
Then Admin receives 200 OK Status with response body.

@Get_Deleted_BatchID
Scenario: Check if admin able to retrive a batch after deleting the batch
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for deleted BatchID
Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body.

@Get_Invalid_BatchID
Scenario: Check if admin able to retrieve a batch with invalid BATCH ID
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for invalid BatchID
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_BatchID_Invalid_EndPoint
Scenario: Check if admin able to retrieve a batch with invalid endpoint
Given Admin creates GET Request
When Admin sends HTTPS Request with Invalid endpoint for BatchID
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_Valid_BatchName
Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for Valid BatchName
Then Admin receives 200 OK Status with response body.

@Get_Invalid_BatchName
Scenario: Check if admin able to retrieve a batch with invalid BATCH NAME
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for InValid BatchName
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_BatchName_Invalid_EndPoint
Scenario: Check if admin able to retrieve a batch with invalid endpoint
Given Admin creates GET Request
When Admin sends HTTPS Request with Invalid endpoint for Valid BatchName 
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_Deleted_BatchName
Scenario: Check if admin able to retrive a deleted batch  using batch name
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for Valid BatchName
Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body.

@Get_Valid_ProgramID
Scenario: Check if admin able to retrieve a batch with valid Program ID
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for ProgramID
Then Admin receives 200 OK Status with response body. 

@Get_InValid_ProgramID_Valid_EndPoint
Scenario: Check if admin able to retrieve a batch with invalid Program Id
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for Invalid ProgramID
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_Invalid_EndPoint_Valid_ProgramID
Scenario: Check if admin able to retrieve a batch with invalid endpoint for ProgramID
Given Admin creates GET Request
When Admin sends HTTPS Request with invalid endpoint for ProgramID
Then Admin receives 404 Not Found Status with message and boolean success details

@Get_Deleted_ProgramID
Scenario: Check if admin able to retrive a batch after the programID is deleted
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for deleted ProgramID
Then Admin receives 404 Not Found Status with message and boolean success details




