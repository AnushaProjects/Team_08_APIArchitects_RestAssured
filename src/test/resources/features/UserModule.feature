#Feature: User Module



  
#
  #GET Request getAllUsersWithRoles NEGATIVE
  #@GetRequest_AllRoles
  #Scenario: Check if admin able to retrieve all Admins with roles with invalid endpoint
    #Given User creates request for the LMS API endpoint with "valid credentials"
    #When User  sends HTTPS Request with "invalid endpoint"
    #Then Admin receives status 404 with Not Found error message
    #
    #GET Request getAllUsersWithRoles NEGATIVE
    #@GetRequest_AllRoles
      #Scenario: Check if admin is able to retreive all the available roles with Authorization
    #Given User creates request for the LMS API endpoint with invalid credentials
    #When User  sends HTTPS Request with "valid endpoint"
    #Then Admin receives status 401 with Unauthorized message
#


    