Feature: ALl GetAll Request for Batch Module without authentication

#Background:
#Scenario: Admin login without authentication
#Given Admin creates GET Request without autherization
#When Admin sends HTTPS Request with endpoint
#Then Admin receives 401  Status with error message unauthorized.

@Get_AllBatch_Without_Autherization
Scenario: Check if admin able to retrieve all batches without Authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request for GetAll_batch with valid endpoint
Then Admin receives 401  Status with error message unauthorized.

@Get_BatchID_Without_Autherization
Scenario: Check if admin able to retrieve a batch with batch ID without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for BatchID
Then Admin receives 401  Status with error message unauthorized.

@Get_BatchName_Without_Autherization
Scenario: Check if admin able to retrieve a batch without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for Valid BatchName
Then Admin receives 401  Status with error message unauthorized.

@Get_ProgramID_Without_Autherization
Scenario: Check if admin able to retrieve a batch without authorization
Given Admin creates GET Request without autherization
When Admin sends HTTPS Request with endpoint for ProgramID
Then Admin receives 401  Status with error message unauthorized.