

Feature: Logout

    
   @logout 
  Scenario: Get log out- Check if Admin able to logout 
Given Admin creates request with valid endpoint for logout
When Admin calls Get Https method with valid endpoint
Then Admin receives 200 ok  and response with "Logout successful"

@logoutwithInvalid
Scenario: Get log out- Check if Admin able to logout  with invalid endpoint 
Given Admin creates request with valid endpoint for logout
When Admin calls Get Https method withinvalid endpoint
Then Admin receives 404 Not found


@logoutwithnoauth
Scenario: Get log out- Check if Admin able to logout with No Auth 
Given Admin creates request with valid endpoint for logout
When Admin calls Get Https method with valid endpoint no auth
Then Admin receives 401  unauthorized