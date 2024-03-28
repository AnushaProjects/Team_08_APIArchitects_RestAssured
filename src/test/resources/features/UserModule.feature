Feature: User Module

  @GetRequest_AllRoles
  Scenario: Check if admin is able to retreive all the available roles with Authorization
    Given User creates request for the LMS API endpoint with Authorization
    When User  sends HTTPS Request with GET All Roles endpoint
    Then User receives status code 200 with response body for viewing an User by Role

