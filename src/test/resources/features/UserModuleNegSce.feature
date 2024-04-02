Feature: Validating Negative Scenarios for GET, PUT and DELETE requests
#PUT REQUEST  (Update Admin Role ID) run update Admin id request twice
#
#Scenario: Check if admin is able to update a Admin with Admin Role Id with already existing Admin role ID
#Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
#When Admin sends HTTPS Request with Update Admin Role ID endpoint
#Then Admin receives 400 Bad Request Status with message and boolean success details

#Scenario: Check if admin is able to update role id of a Admin by valid Admin id
#Given Admin creates PUT Request with invalid request body
#When Admin sends HTTPS  "PUT-Update Admin Role ID" Request with endpoint
#Then Admin receives 400 Bad Request Status with message and boolean success details#
#
#Scenario: Check if admin is able to update a Admin with Admin Role Id with already existing Admin role ID
#Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
#When Admin sends HTTPS Request with Update Admin Role ID endpoint
#Then Admin receives 400 Bad Request Status with message and boolean success details
#
#Scenario: Check if admin is able to update role id of a Admin with invalid Admin id
#Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
#When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role ID" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details#
#
#Scenario: Check if admin is able to update role id of a Admin by valid Admin id with no authorization
#Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
#When Admin sends HTTPS "PUT - Update Admin Role ID" Request with endpoint unauthorized
#Then Admin receives status 401 with Unauthorized Message

#Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates Update Admin Role ID Request with valid userRoleList in request body
#When Admin sends HTTPS Request with invalid "PUT - Update Admin Role ID" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details

#PUT REQUEST (Update Admin role status by Admin ID)

#Scenario: Check if admin is able to update role status of a Admin with valid Admin id and invalid role status
#Given Admin creates Update User Role Status PUT Request with in valid data in request body 
#When Admin sends HTTPS Request with Update User Role Status endpoint
#Then Admin receives 400 Bad Request Status with message and boolean success details
#
#Scenario: Check if admin is able to update role status of a Admin with invalid Admin id
    #Given Admin creates Update User Role Status PUT Request with valid data in request body 
    #When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin role status by Admin ID" endpoint
    #Then Admin receives 404 Bad Request Status with message and boolean success details
    
    #not done
    #Scenario: Check if admin is able to update role status of a Admin for nonexisting/unassigned RoleID
    #Given Admin creates PUT Request with nonexisting/unassigned RoleID
    #When Admin sends HTTPS Request with endpoint
    #Then Admin receives status 404 with Not Found error message
    
    #Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    #Given Admin creates Update User Role Status PUT Request with valid data in request body 
    #When Admin sends HTTPS "PUT - Update Admin role status by Admin ID" Request with endpoint unauthorized
    #Then Admin receives status 401 with Unauthorized Message
    
    #
#Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates Update User Role Status PUT Request with valid data in request body
#When Admin sends HTTPS Request with invalid "PUT - Update Admin role status by Admin ID" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details
    
    #PUT Request - (Update Admin Role Program Batch status)
    
    #Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    #Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
    #When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin Role Program Batch status" endpoint
    #Then Admin receives 404 Bad Request Status with message and boolean success details
    
      #Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    #Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body 
    #When Admin sends HTTPS "PUT - Update Admin Role Program Batch status" Request with endpoint unauthorized
    #Then Admin receives status 401 with Unauthorized Message   
    #
    #Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body
#When Admin sends HTTPS Request with invalid "PUT - Update Admin Role Program Batch status" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details
    
Scenario: Check if admin is able to assign Admin to with program/batch by Admin Id
Given Admin creates PUT Request with invalid data in request body
When Admin sends Admin Role Program Batch status HTTPS Request with endpoint
Then Admin receives 400 Bad Request Status with message and boolean success details

		#PUT REQUEST - (Update Admin login status) Negative Scenario
  #Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    #Given Admin creates Update Admin login status PUT Request with valid data in request body
    #When Admin sends HTTPS Request with invalid AdminId and valid "PUT - Update Admin login status" endpoint
    #Then Admin receives 404 Bad Request Status with message and boolean success details
    
      #Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    #Given Admin creates Update Admin login status PUT Request with valid data in request body
    #When Admin sends HTTPS "PUT - Update Admin login status" Request with endpoint unauthorized
    #Then Admin receives status 401 with Unauthorized Message
    #
    #Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates Update Admin login status PUT Request with valid data in request body
#When Admin sends HTTPS Request with invalid "PUT - Update Admin login status" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details
    
    #PUT Request (Update Admin info) Negative Scenario
      #Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    #Given Admin creates PUT Request with valid data in request body
    #When Admin sends HTTPS Request with invalid AdminId and valid "PUT - User" endpoint
    #Then Admin receives 404 Bad Request Status with message and boolean success details
    
      #Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    #Given Admin creates PUT Request with valid data in request body 
    #When Admin sends HTTPS "PUT - User" Request with endpoint unauthorized
    #Then Admin receives status 401 with Unauthorized Message
    #
    #Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates PUT Request with valid data in request body
#When Admin sends HTTPS Request with invalid "PUT - User" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details

		#Delete Negative Scenario
 #Scenario: Check if admin is able to assign Admin to with program/batch by invalid Admin Id
    #Given Admin creates DELETE Request to delete Admin details
    #When Admin sends HTTPS Request with invalid AdminId and valid "DELETE - User" endpoint
    #Then Admin receives 404 Bad Request Status with message and boolean success details

  #Scenario: Check if admin is able to update role status of a Admin with valid Admin id with no authorization
    #Given Admin creates DELETE Request to delete Admin details
    #When Admin sends HTTPS "DELETE - User" Request with endpoint unauthorized
    #Then Admin receives status 401 with Unauthorized Message
    
       #Scenario: Check if admin is able to update a Admin role ID with valid Admin role ID with invalid endpoint
#Given Admin creates DELETE Request to delete Admin details
#When Admin sends HTTPS Request with invalid "DELETE - User" endpoint
#Then Admin receives 404 Bad Request Status with message and boolean success details




