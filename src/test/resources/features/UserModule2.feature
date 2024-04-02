Feature: User Module

  @CreatingUserIdWithAllFields
  Scenario Outline: Check if admin is able to create a new Admin with valid endpoint and request body with mandatory fields and Additional fields
    Given Admin creates POST request with all mandatory fields and additional fields
    When Admin sends HTTPS Request with endpoint
    Then Admin receives 201 Created Status with response body and Save userId for "<Scenario>".

    Examples: 
      | Scenario |
      | Positive |
      | Negative |

  #GET Request getAllUsersWithRoles POSITIVE
  Scenario: Check if admin is able to retreive all the available roles with Authorization
    Given User creates getAllUsersWithRoles request for the LMS API endpoint with "valid credentials"
    When User  sends HTTPS Request with "valid endpoint"
    Then Admin receives 200 OK Status with response body for getAllUsersWithRoles

  #REQUEST_(Admin with filters)
  @GetReqWithAdminfilters
  Scenario: Check if admin is able to retrieve all Admins with filters
    Given Admin creates GET Adminfilters Request
    When Admin sends HTTPS Request with Admins with filters "valid endpoint"
    Then Admin receives 200 OK Status with response body for GetReqWithAdminfilters

  #PUT Request (Update Admin Role ID) POSITIVE
  Scenario: Check if admin is able to update role id of a Admin by valid Admin id
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with Update Admin Role ID endpoint
    Then Admin receives 200 OK Status with Update Admin Role ID response body

  #PUT Request (Update User Role Status) POSITIVE
  Scenario: Check if admin is able to update role status of a Admin with valid Admin id
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with Update User Role Status endpoint
    Then Admin receives 200 OK Status with "PUT - Update Admin role status by Admin ID" response body

  #PUT REQUEST (Update Admin Role Program Batch status) POSITIVE - Needs program id /batch id from swati and priyanka code
  #Scenario: Check if admin is able to assign Admin to with program/batch by Admin Id
    #Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    #When Admin sends HTTPS Request with Update Admin Role Program Batch status endpoint
    #Then Admin receives 200 OK Status with response body for Update Admin Role Program Batch status PUT Request
    #Then Admin receives 200 OK Status with "PUT - Update Admin Role Program Batch status" response body

  #PUT REQUEST (Update Admin login status) POSITIVE
  Scenario: Check if admin is able to update the Admin login status by Admin ID
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with Update Admin login status endpoint
    Then Admin receives 200 OK Status with "Update Admin login status" response body

  #PUT Request (Update Admin info) POSITIVE
  Scenario: Check if admin is able to update Admin details with Admin id and valid data in all fields
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS request with put endpoint
    Then Admin receives 200 OK Status with "PUT - User" response body

  #Delete Request POSITIVE
  Scenario: Check if Admin able to delete a Admin with valid Admin Id
    When Admin sends HTTPS request with delete endpoiint
    Then Admin receives 200 OK Status with "response message" for delete user request

  #PUT Request - (Update Admin Role ID) NEGATIVE SCENARIOS
  Scenario: Check if admin is able to update a Admin with Admin Role Id with already existing Admin role ID
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body with existing id
    When Admin sends HTTPS Request with Update Admin Role ID endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success details for Update Admin Role ID

  Scenario: Check if admin is able to update role id of a Admin by valid Admin id
    Given Admin creates Update Admin Role ID PUT Request with invalid Update Admin Role ID request body
    When Admin sends HTTPS Request with Update Admin Role ID endpoint

  #PUT Request (Update User Role Status) NEGATIVE
  #Scenario: Check if admin is able to update role status of a Admin with valid Admin id and invalid role status
    #Given Admin creates Update User Role Status PUT Request with in valid role status in request body
    #When Admin sends HTTPS Request with Update User Role Status endpoint

  # All PUT Requests Invalid AdminID scenario
  Scenario: Check if admin is able to update role id of a Admin with invalid Admin id
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role ID" endpoint
    Then Admin receives 404 Bad Request Status with message and boolean success details for update user

  Scenario: Check if admin is able to update role status of a Admin with invalid Admin id
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin role status by Admin ID" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role Program Batch status" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin login status" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid AdminId and valid "PUT - User" endpoint

  Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    When Admin sends HTTPS Request with invalid AdminId and valid "DELETE - User" endpoint

  #All PUT Requests Invalid endpoint scenario
  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin Role ID" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin role status by Admin ID" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin Role Program Batch status" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - Update Admin login status" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS Request with invalid "PUT - User" endpoint

  Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
    When Admin sends HTTPS Request with invalid "DELETE - User" endpoint

  #All PUT Requests with No Authorization
  Scenario: Check if admin is able to update role id of a Admin by valid Admin id with no authorization
    Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
    When Admin sends HTTPS "PUT - Update Admin Role ID" Request with endpoint unauthorized
    Then Admin receives status 401 with Unauthorized Message

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update User Role Status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin role status by Admin ID" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin Role Program Batch status" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates Update Admin login status PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - Update Admin login status" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    Given Admin creates PUT Request with valid data in request body
    When Admin sends HTTPS "PUT - User" Request with endpoint unauthorized

  Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    When Admin sends HTTPS "DELETE - User" Request with endpoint unauthorized
