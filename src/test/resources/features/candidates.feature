Feature: Candidate Management in Event

  @candidate
  Scenario: Invite single candidate
    Given I am on the Canditate page
    And I click on Invite Candidate button
    And I select "Single-Invite" as option
    And I enter First name as "Naveen Kumar"
    And I enter Last name as "S"
    And I enter Email Id as "naveen+02@examly.in"
    And I select Gender as "Male"
    And I enter Date of birth as "24-02-2003"
    And I enter mobile number as "8855885548"
    And I select Event category as "Technical"
    And I select Event name as "GG Campus Drive 2808"
    And I select Source type as "Data"
    And I select Source name as "Linkedin"
    Then I click Send Invite button
