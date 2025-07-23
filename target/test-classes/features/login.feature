Feature: Login and OTP verification

  Scenario: Login with valid credentials and valid OTP
    Given I launch the application
    When I enter username and password
    And I click the login button
    And I enter OTP "123456"
    And I click the Verify Account button
    Then I should see Events page

  Scenario: Login with valid credentials and invalid OTP
    Given I launch the application
    When I enter username and password
    And I click the login button
    And I enter OTP "000000"
    And I click the Verify Account button
    Then I should see an OTP error message
