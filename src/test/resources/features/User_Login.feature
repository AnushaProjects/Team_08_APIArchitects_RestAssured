Feature: User Login

  Background: 

  Scenario: Check if Admin able to generate token with valid credential
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives 200 created with auto generated token

  Scenario: Check if Admin able to generate token with invalid endpoint
    Given Admin creates request with "valid" credentials
    When Admin Admin calls Post Https method  with "Invalid endpoint"
    Then Admin Admin receives Admin receives 401 unauthorized
    
   Scenario: Check if Admin able to generate token with invalid credentials
    Given Admin creates request with "invalid" credentials
    When Admin Admin calls Post Https method  with "valid endpoint"
    Then Admin Admin receives Admin receives 401 BadRequest