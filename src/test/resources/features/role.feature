Feature: Create Role

  Scenario: Create a new role successfully
    Given I am on the Roles page
    When I click on the "New role" button
    And I enter role name "QA Engineer"
    And I enter role type "Full Time"
    And I enter work mode as "Hybrid"
    And I enter country as "India"
    And I enter state as "Tamil Nadu"
    And I enter city as "Coimbatore"
    And I enter business unit as "uuhhd"
    And I click the "Save" button
    Then I should see a success message "Role created successfully"


@roleCreatewithEmptyValue
  Scenario: Create role with invalid data
    Given I am on the Roles page
    When I click on the "New role" button
    And I click the "Save" button
    Then I should see role should not be created
