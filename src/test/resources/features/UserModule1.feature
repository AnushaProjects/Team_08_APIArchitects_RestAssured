Feature: User Module


Background: 
  Scenario: User Login with given request.
    Given User Login to LMS Application
    When User enter valid credentials
    Then User receives status code 200 with response body with BearerToken
    
  @GetRequest_AllRoles
  Scenario: Check if admin is able to retreive all the available roles with Authorization

    Given User creates request for the LMS API endpoint with Authorization
    When User  sends HTTPS Request with GET All Roles endpoint
    Then User receives status code 200 with response body for viewing an User by Role
