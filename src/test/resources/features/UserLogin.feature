Feature: User Login

  Background: 
  Scenario: User Login with given request.
    Given User Login to LMS Application
    When User enter valid credentials
    Then User receives status code 200 with response body with BearerToken