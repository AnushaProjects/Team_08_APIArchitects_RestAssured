Feature: Create batch program
@USERL_LOGIN
Scenario: User is logged in
Given Admin creates request with valid credentials
When Admin Admin calls Post Https method  with valid endpoint
Then Admin Admin receives 200 created with auto generated token
@Post-PROGRAM
Scenario: Creating a program
Given Admin creates POST program Request  with valid data in request body 
When Admin sends HTTPS Request with program endpoint 
 Then Admin receives 201 Created Status with response body in program.                                                          

@Post-BATCH-1
Scenario: Check if admin able to create a Batch with valid endpoint and request body without authorization.
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with endpoint without authorization
 Then Admin receives 401 Unauthorized
  @Post-BATCH-2
 Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with endpoint 
 Then Admin receives 201 Created Status with response body.                                                          
  @Post-BATCH-3
 Scenario: Check if admin able to create a Batch with valid endpoint and request body (existing value in Batch Name)
 Given Admin creates POST batch Request  with existing value in request body 
 When Admin sends HTTPS batch Request with endpoint with existing value in batchname
 Then Admin receives 400 Bad Request Status with message and boolean success details
 @Post-BATCH-4
 Scenario: Check if admin able to create a Batch missing mandatory fields in request body
 Given Admin creates POST batch Request  with invalid data in request body 
 When Admin sends HTTPS batch Request with endpoint with missing mandatory fields
 Then Admin receives 400 Bad Request Status with message and boolean success details
 @Post-BATCH-5
 Scenario: Check if admin able to create a batch with invalid endpoint
 Given Admin creates POST batch Request  with valid data in request body 
 When Admin sends HTTPS batch Request with invalid endpoint 
 Then Admin receives 404 not found Status 
 @Post-BATCH-6
 Scenario: Check if admin able to create a batch with missing additional fields
 Given Admin creates POST batch Request with missing additional fields
 When Admin sends HTTPS batch Request with endpoint 
 Then Admin receives 201 Created Status with response body for missing additional fields.                                                          
  @Post-BATCH-7
 Scenario: Check if admin able to create a batch with invalid data in request body
 Given Admin creates POST batch Request  with invalid data in request body
 When Admin sends HTTPS batch Request with endpoint and invalid data
 Then Admin receives 400 Bad Request Status with message and boolean success details
  @Post-BATCH-8
 Scenario: Check if admin able to create a batch  with inactive program ID
 Given Admin creates POST batch Request with inactive program id
 When Admin sends HTTPS batch Request with endpoint and invalid data
 Then Admin receives 400 Bad Request Status with message and boolean success details
 
 
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
Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_BatchID_Invalid_EndPoint
Scenario: Check if admin able to retrieve a batch with invalid endpoint
Given Admin creates GET Request
When Admin sends HTTPS Request with Invalid endpoint for BatchID
Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_Valid_BatchName
Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for Valid BatchName
Then Admin receives 200 OK Status with response body.

@Get_Invalid_BatchName
Scenario: Check if admin able to retrieve a batch with invalid BATCH NAME
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for InValid BatchName
Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_BatchName_Invalid_EndPoint
Scenario: Check if admin able to retrieve a batch with invalid endpoint
Given Admin creates GET Request
When Admin sends HTTPS Request with Invalid endpoint for Valid BatchName
Then Admin receives 404 Not Found Status with the message and boolean success details

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

#@Get_InValid_ProgramID_Valid_EndPoint
#Scenario: Check if admin able to retrieve a batch with invalid Program Id
#Given Admin creates GET Request
#When Admin sends HTTPS Request with endpoint for Invalid ProgramID
#Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_Invalid_EndPoint_Valid_ProgramID
Scenario: Check if admin able to retrieve a batch with invalid endpoint for ProgramID
Given Admin creates GET Request
When Admin sends HTTPS Request with invalid endpoint for ProgramID
Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_Deleted_ProgramID
Scenario: Check if admin able to retrive a batch after the programID is deleted
Given Admin creates GET Request
When Admin sends HTTPS Request with endpoint for deleted ProgramID
Then Admin receives 404 Not Found Status with the message and boolean success details

@Get_AllBatch_Without_Autherization
Scenario: Check if admin able to retrieve all batches without Authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request for GetAll_batch with valid endpoint
Then Admin receives 401 Unauthorized access

@Get_BatchID_Without_Autherization
Scenario: Check if admin able to retrieve a batch with batch ID without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for BatchID
Then Admin receives 401 Unauthorized access

@Get_BatchName_Without_Autherization
Scenario: Check if admin able to retrieve a batch without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for Valid BatchName
Then Admin receives 401 Unauthorized access

@Get_ProgramID_Without_Autherization
Scenario: Check if admin able to retrieve a batch without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for ProgramID
Then Admin receives 401 Unauthorized access

  
  @PUT-BATCH-1
 Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body without authorization
 Given Admin creates PUT batch Request with valid BatchId and Data
 When Admin sends HTTPS batch Request with update endpoint with no authorization
 Then Admin receives 401 Unauthorized
  @PUT-BATCH-2
 Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
 Given Admin creates PUT batch Request with valid BatchId and Data
 When Admin sends HTTPS batch Request with update endpoint
 Then Admin receives 200 OK Status with updated value in response body.                                         
 @PUT-BATCH-3
 Scenario: Check if admin able to update a Batch with invalid batchID and mandatory fields in request body
 Given Admin creates PUT batch Request with invalid BatchId and valid Data
  When Admin sends HTTPS batch Request with update endpoint and update batchid
  Then Admin receives 404 Not Found Status with message and boolean success details
 
 @PUT-BATCH-4
 Scenario: Check if admin able to update a Batch with valid batchID and missing mandatory fields request body
 Given Admin creates PUT batch Request with valid batch Id and missing mandatory fields
 When Admin sends HTTPS batch Request with update endpoint and update batchid
 Then Admin receives 400 Bad Request Status with message and boolean success details
 @PUT-BATCH-5
 Scenario: Check if admin able to update a batch with invalid data
 Given Admin creates PUT batch Request with invalid data
 When Admin sends HTTPS batch Request with update endpoint
Then Admin receives 400 Bad Request Status with message and boolean success details
 @PUT-BATCH-6
 Scenario: Check if admin able to update a Batch with invalid enpoint
 Given Admin creates PUT batch Request with valid BatchId and Data
 When Admin sends HTTPS PUT batch Request with invalid endpoint
 Then Admin receives 404 not found Status
 @PUT-BATCH-7
 Scenario: Check if admin able to update a Batch with a valid batchID and deleted programID field  in the request body with other mandatory fields
 Given Admin creates PUT batch Request with Valid batch Id and delete programId field
 When Admin sends HTTPS batch Request with update endpoint
 Then Admin receives 400 Bad Request Status with message and boolean success details
 @PUT-BATCH-8
 Scenario: Check if admin able to update a Batch with a deleted batchID in the endpoint
 Given Admin creates PUT batch Request with deleted batch Id
  When Admin sends HTTPS batch Request with update endpoint and update batchid
 Then Admin receives 200 Ok status with message
 
 
 @Delete_Valid_BatchID
Scenario: Check if admin able to delete a Batch with valid Batch ID
Given Admin creates DELETE Request
When Admin sends HTTPS Request with valid BatchId and endpoint
Then Admin receives 200 Ok status with the message
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
Then Admin receives 401 Unauthorized access







 
 
 
 
 
 
 
 
 
 
 
 
 