Feature: Create New Event

    
@event

  Scenario: Successfully create a new event in In-campus type
    Given I am on the Event page
    And I select "100" items per page in the event table
    When I click on the "New Event" button on Event page
    And I enter event title as "GG Campus Drive 36"
    And I select event start date as "2025-07-27 12:30 PM"
    And I select event end date as "2025-07-28 12:00 PM"
    And I select registration start date as "2025-07-25 12:00 PM"
    And I select registration end date as "2025-07-26 12:00 PM"
    And I select job role as "QA Engineer, QC Engineer"   
    And I enter work experience as "2"
    And I enter minimum salary as "300000"
    And I enter maximum salary as "600000"
    And I select event type as "In-Campus"
    And I select event category as "Technical"
    And I select email template as "Test Link"
    And I select registration form as "demo Copy"
    And I select institution Name as "AAA COLLEGE OF ENGINEERING AND TECHNOLOGY,  SIR VISHVESHWARIAH INSTITUE OF SCIENCE AND TECHNOLOGY "
    And I enter institution address as "Addressssssssss"
    And I submit the event
    Then I should see event created successfully message
    Then I search and verify event "GG Campus Drive 36" is visible


@event

  Scenario: Successfully create a new event in Face to Face type
    Given I am on the Event page
    And I select "100" items per page in the event table
    When I click on the "New Event" button on Event page
    And I enter event title as "GG Campus Drive 33"
    And I select event start date as "2025-07-28 10:00 AM"
    And I select event end date as "2025-07-29 04:00 PM"
    And I select registration start date as "2025-07-26 10:00 AM"
    And I select registration end date as "2025-07-27 04:00 PM"
    And I select job role as "QA Engineer, QC Engineer"
    And I enter work experience as "1"
    And I enter minimum salary as "250000"
    And I enter maximum salary as "500000"
    And I select event type as "Face to Face"
    And I select event category as "Technical"
    And I select registration form as "demo Copy"
    And I select email template as "Test Link"
    And I select institution Name as "AAA COLLEGE OF ENGINEERING AND TECHNOLOGY"
    And I enter institution address as "Face to face venue address"
    And I submit the event
    Then I should see event created successfully message
    Then I search and verify event "GG Campus Drive 33" is visible


@event

  Scenario: Successfully create a new event in Test type
    Given I am on the Event page
    And I select "100" items per page in the event table
    When I click on the "New Event" button on Event page
    And I enter event title as "GG Campus Drive 34"
    And I select event start date as "2025-07-27 12:30 PM"
    And I select event end date as "2025-07-28 12:00 PM"
    And I select registration start date as "2025-07-25 12:00 PM"
    And I select registration end date as "2025-07-26 12:00 PM"
    And I select job role as "QA Engineer"
    # QC Engineer
    And I select event category as "Technical"
    And I enter work experience as "2"
    And I enter minimum salary as "300000"
    And I enter maximum salary as "600000"
    And I select event type as "Test"
    And I select registration form as "demo Copy"
    And I select email template as "Test Link"
    And I submit the event
    Then I should see event created successfully message
    Then I search and verify event "GG Campus Drive 34" is visible