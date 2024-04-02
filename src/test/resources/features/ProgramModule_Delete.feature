Feature: Program module Delete Request

Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with valid credentials
    When Admin Admin calls Post Https method  with valid endpoint
    Then Admin Admin receives 200 created with auto generated token
 
 @Delete_ValidName_Valid_EndPoint  
 Scenario: Check if Admin able to delete a program with valid programName  
 Given Admin creates Delete Request for Program
 When Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point
 Then Admin receives 200 Ok status with message for Delete

#@Delete_ValidName_Invalid_EndPoint
#Scenario: Check if Admin able to delete a program with valid LMS API,invalid programName
#Given Admin creates Delete Request for Program 
#When Admin sends HTTPS Request for Delete with Valid Program Name and Invalid End Point
#Then Admin receives 404 Not Found Status for Delete

#@Delete_ValidName_Without_Autherization
#Scenario: Check if Admin able to delete a Program Name without Authorization
#Given Admin creates Delete Request for Program
#When Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point
#Then Admin receives 401 Unauthorized for Delete

#@Delete_Valid_ProgramID_Valid_EndPoint
#Scenario: Check if Admin able to delete a program with valid program ID
#Given Admin creates Delete Request for Program
#When Admin sends HTTPS Request for Delete with Valid Program_ID and Valid End Point
#Then Admin receives 200 Ok status with message for Delete

#@Delete_Valid_ProgramID_InValid_EndPoint
#Scenario: Check if Admin able to delete a program with valid program ID
#Given Admin creates Delete Request for Program
#When Admin sends HTTPS Request for Delete with Valid Program_ID and Invalid End Point
#Then Admin receives 200 Ok status with message for Delete

#@Delete_InValid_ProgramID_Valid_EndPoint
#Scenario: Check if Admin able to delete a program with valid LMS API,invalid program ID
#Given Admin creates Delete Request for Program
#When Admin sends HTTPS Request for Delete with InValid Program_ID and Valid End Point
#Then Admin receives 404 Not Found Status for Delete

#@Delete_Valid_ProgramID_Without_Autherization
#Scenario: Check if Admin able to delete a Program_Id without Authorization
#Given Admin create DELETE Request without Autherization for Program
#When Admin sends HTTPS Request for Delete with Valid Program_ID and Valid End Point
#Then Admin receives 401 Unauthorized for Delete
