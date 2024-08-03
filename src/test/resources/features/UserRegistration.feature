Feature: User registration
  Scenario: Successful registration
    Given the user opens application url and enters into registration page
    When the user enter all required detials
    And the user clicks the register button
    Then if the user doesn't exists then should get success messgae like (successMessage: "Your registration completed") otherwise error like (errorMessage: "The specified email already exists")


