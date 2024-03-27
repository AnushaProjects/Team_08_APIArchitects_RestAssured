Feature: User Login

  Background: 

  Scenario: User Login with given request.
    Given Admin creates request with valid credentials
    When Admin Admin calls Post Https method  with valid endpoint
    Then Admin Admin receives 201 created with auto generated token
