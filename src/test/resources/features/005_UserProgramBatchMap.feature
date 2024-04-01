Feature: User Program Batch Map


#Scenario: Check if Admin able to generate token with valid credential
    #Given Admin creates request with "valid" credentials
    #When Admin Admin calls Post Https method  with "valid endpoint"
    #Then Admin Admin receives 200 created with auto generated token

@GetAllUserProgramBatch
Scenario: Check if admin is able to retreive all Admins with assigned program batches
Given Admin  creates GET Request to retrieve all Admins assigned to programsbatches 
When Admin  sends HTTPS Request 
Then Admin  receives 200 OK

@GetAllUserProgramBatchNOAuth
Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin  creates GET Request to retrieve all Admins assigned to programsbatches 
When Admin sends HTTPS Request  with NoAuth
Then Admin  receives status 401 with Unauthorized message


#@GetAllUserProgramBatchByValidAdmin
#Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
#Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "valid" AdminID 
#When Admin sends HTTPS Request 
#Then Admin  receives 200 OK


@GetAllUserProgramBatchByInvalidAdmin
Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "invalid" AdminID 
When Admin sends HTTPS Request 
Then Admin receives 404 Not Found

@GetAllUserProgramBatchByAdminNOAuth
Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by "valid" AdminID
When Admin sends HTTPS Request  with NoAuthAdmin
Then Admin  receives status 401 with Unauthorized message






