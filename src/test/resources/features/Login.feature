Feature: User Login
  Scenario: Successful Login
    Given the user opens the application and go to login page
    When the user enter valid username and password (username : "mohantest@gmail.com", password: "Mohan123")
    And the user clicks the login button
    Then the user should navigate to my account page
    And the user should see the username in my account page
