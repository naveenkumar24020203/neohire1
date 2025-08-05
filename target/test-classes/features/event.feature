Feature: Event Page scenarios

    
@eventCreate
Scenario Outline: Successfully create a new event of different types
  Given I am on the Event page
  And I select "100" items per page in the event table
  When I click on the "New Event" button on Event page
  And I enter event title as "<eventTitle>"
  And I select event start date as "<eventStart>"
  And I select event end date as "<eventEnd>"
  And I select registration start date as "<regStart>"
  And I select registration end date as "<regEnd>"
  And I select job role as "<jobRoles>"
  And I enter work experience as "<experience>"
  And I enter minimum salary as "<minSalary>"
  And I enter maximum salary as "<maxSalary>"
  And I select event type as "<eventType>"
  And I select event category as "<eventCategory>"
  And I select email template as "<emailTemplate>"
  And I select registration form as "<registrationForm>"
  And I select institution Name as "<institutionName>"
  And I enter institution address as "<institutionAddress>"
  And I submit the event
  Then I should see event created successfully message
  Then I search and verify event "<eventTitle>" is visible

Examples:
  | eventTitle           | eventStart           | eventEnd             | regStart            | regEnd              | jobRoles                  | experience | minSalary | maxSalary | eventType     | eventCategory | emailTemplate | registrationForm | institutionName                                                                                     | institutionAddress           |
  | GG Campus Drive 2808055 | 2026-08-28 12:30 PM  | 2026-08-29 12:00 PM  | 2026-08-26 12:00 PM | 2026-08-27 12:00 PM | QA Engineer, QC Engineer | 2          | 300000    | 600000    | In-Campus     | Technical     | Test Link     | demo Copy        | AAA COLLEGE OF ENGINEERING AND TECHNOLOGY,  SIR VISHVESHWARIAH INSTITUE OF SCIENCE AND TECHNOLOGY | Addressssssssss             |
  # | GG Campus Drive 280805   | 2026-08-28 10:00 AM  | 2026-08-29 04:00 PM  | 2026-08-26 10:00 AM | 2026-08-27 04:00 PM | QA Engineer, QC Engineer | 2          | 250000    | 500000    | Face to Face  | Technical     | Test Link     | demo Copy        | AAA COLLEGE OF ENGINEERING AND TECHNOLOGY                                                          | Face to face venue address  |
  # | GG Campus Drive 280806   | 2026-08-27 12:30 PM  | 2026-08-28 12:00 PM  | 2026-08-25 12:00 PM | 2026-08-26 12:00 PM | QA Engineer              | 2          | 300000    | 600000    | Test          | Technical     | Test Link     | demo Copy        |                                                                                                    |                              |


@eventDelete
Scenario: delete a event
  # delete am event
  Given I am on the Event page
  And I select "100" items per page in the event table
  Then I search and verify event "<deleteEventTitle>" is visible
  And I delete the event named "<deleteEventTitle>"
  Then I search and verify event "<deleteEventTitle>" is not visible

Examples:

| deleteEventTitle |
| GG Campus Drive 280801 |
| GG Campus Drive 280802 |
| GG Campus Drive 280803 |
| GG Campus Drive 280804 |
| GG Campus Drive 280805 |
| GG Campus Drive 280806 |




@eventCreatewithEmptyValue
Scenario Outline: Create Event with empty data
  Given I am on the Event page
  And I select "100" items per page in the event table
  When I click on the "New Event" button on Event page
  And I submit the event
  Then I should see event should not be created
