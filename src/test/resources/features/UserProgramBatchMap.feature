Feature: User Program Batch Map


  Scenario: User Login with given request.
    Given User Login to LMS Application
    When User enter valid credentials
    Then User receives status code 200 with response body with BearerToken

Scenario: Check if admin is able to retreive all Admins with assigned program batches
Given Admin creates GET Request to retrieve all Admins assigned to programs/batches 
When Admin sends HTTPS Request 
Then Admin receives 200 OK

Scenario: Check if admin is able to retreive all Admins with assigned program batches with No Authorization
Given Admin creates GET Request to retrieve all Admins assigned to programs/batches with No Authorization
When Admin sends HTTPS Request  
Then Admin receives status 401 with Unauthorized message


Scenario: Check if admin is able to retreive assigned program batches for valid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by AdminId
When Admin sends HTTPS Request 
Then Admin receives 200 OK

Scenario: Check if admin is able to retreive assigned program batches for invalid AdminId
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by invalid AdminID 
When Admin sends HTTPS Request 
Then Admin receives 404 Not Found

Scenario: Check if admin is able to retreive assigned program batches for valid AdminId with No authorization
Given Admin creates GET Request to retrieve Admin assigned to Program/Batch by valid AdminID with No Authorization
When Admin sends HTTPS Request 
Then Admin receives status 401 with Unauthorized message


Scenario: Check if admin is able to delete the program batch for a Admin
Given Admin creates DELETE Request to delete Admin assigned to program/batch by AdminId 
When Admin sends HTTPS Request  
Then Admin receives 200 OK

Scenario: Check if admin is able to delete the program batch for invalid Admin
Given Admin creates DELETE Request to delete Admin assigned to program/batch by invalid AdminId
When Admin sends HTTPS Request 
Then Admin receives 404 Not Found

Scenario: Check if admin is able to delete the program batch for valid Admin and No Authorization
Given Admin creates DELETE Request to delete Admin assigned to program/batch by valid AdminId with No Auth
When Admin sends HTTPS Request  
Then Admin receives status 401 with Unauthorized message





Scenario: post request-Check if Admin able to generate token with valid credential
Given Admin creates request with valid credentials to generate token
When Admin calls Post Https method  with valid endpoint 
Then Admin receives 201 created with auto generated token

Scenario: post request-Check if Admin able to generate token with invalid endpoint
Given Admin creates request with valid credentials and invalid endpoint
When Admin calls Post Https method  with invalid endpoint 
Then Admin receives 401 unauthorized

Scenario: post request-Check if Admin able to generate token with invalid credentials
Given Admin creates request with invalid credentials and valid enpoint
When Admin calls Post Https method  with valid endpoint 
Then Admin receives 400 Bad request



Scenario: Get log out- Check if Admin able to logout 
Given Admin creates request with valid endpoint for logout
When Admin calls Get Https method with valid endpoint
Then Admin receives 200 ok  and response with "Logout successful"

Scenario: Get log out- Check if Admin able to logout  with invalid endpoint 
Given Admin creates request with invalid endpoint for logout
When Admin calls Get Https method withinvalid endpoint
Then Admin receives 405 Not found

Scenario: Get log out- Check if Admin able to logout with No Auth 
Given Admin creates request with no Auth for logout
When Admin calls Get Https method with valid endpoint
Then Admin receives 401  unauthorized