@smoke
Feature: Smoke Test for Role creation, Event creation, and Candidate Invite

  Scenario Outline: Create a new role and event successfully
    Given I am on the Roles page
    When I click on the "New role" button
    And I enter role name "<roleName>"
    And I enter role type "<roleType>"
    And I enter work mode as "<workMode>"
    And I enter country as "<country>"
    And I enter state as "<state>"
    And I enter city as "<city>"
    And I enter business unit as "<businessUnit>"
    And I click the "Save" button
    Then I should see a success message "Role created successfully"

    Then I am on the Event page
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
    | roleName        | roleType  | workMode | country | state      | city        | businessUnit | eventTitle            | eventStart           | eventEnd             | regStart            | regEnd              | jobRoles     | experience | minSalary | maxSalary | eventType  | eventCategory | emailTemplate       | registrationForm | institutionName                                                                                      | institutionAddress           |
    | QACb Engineer    | Full Time | Hybrid   | India   | Tamil Nadu | Coimbatore  | uuhhd        | GG Campus Drive 04080405 | 2026-08-28 12:30 PM  | 2026-08-29 12:00 PM  | 2026-08-26 12:00 PM | 2026-08-27 12:00 PM | QACb Engineer | 2          | 300000    | 600000    | In-Campus | Technical     | Test Link            | demo Copy        | AAA COLLEGE OF ENGINEERING AND TECHNOLOGY, SIR VISHVESHWARIAH INSTITUE OF SCIENCE AND TECHNOLOGY | Addressssssssss              |



  Scenario Outline: Invite candidate to the created event
    Given I am on the Canditate page
    And I click on Invite Candidate button
    And I select "<inviteType>" as option
    And I enter First name as "<firstName>"
    And I enter Last name as "<lastName>"
    And I enter Email Id as "<emailId>"
    And I select Gender as "<gender>"
    And I enter Date of birth as "<dob>"
    And I enter mobile number as "<mobile>"
    And I select Event category as "<eventCategory>"
    And I select Event name as "<eventName>"
    And I select Source type as "<sourceType>"
    And I select Source name as "<sourceName>"
    Then I click Send Invite button

    Examples:
      | inviteType    | firstName    | lastName | emailId               | gender | dob         | mobile     | eventCategory | eventName               | sourceType | sourceName |
      | Single-Invite | Naveen Kumar | S        | naveen+02@examly.in   | Male   | 24-02-2003  | 8855885548 | Technical     | GG Campus Drive 04080405 | Data       | Linkedin    |

 Scenario: Create Screening stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Screening" stage named "Screening" with dropdown "Document form"
    Then I should see a success message for stage creation

  Scenario: Create Test stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Test" stage named "Test" with dropdown "Aptitude Assessment" and link "Test 1"
    Then I should see a success message for stage creation

  Scenario: Create Interview stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Interview" stage named "Interview" with dropdown "Feed back form" and link "Interview -1"
    Then I should see a success message for stage creation

  Scenario: Create Offline Interview stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Offline Interview" stage named "Offline Interview" with dropdown "Feed back form" and link "Offline interview"
    Then I should see a success message for stage creation

  Scenario: Create Offer stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Offer" stage named "Offer" with dropdown "offer_form" and link "Job Offer Mail"
    Then I should see a success message for stage creation

  Scenario: Create Onboarding stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Onboarding" stage named "Onboarding"
    Then I should see a success message for stage creation

  Scenario: Create Others stage
    Given I am inside the event page for "GG Campus Drive 04080405"
    And I navigate to "Stages" tab in the event
    And I create a "Others" stage named "Others"
    Then I should see a success message for stage creation



Scenario: Create rule for Eligibility Check stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Eligibility Check" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Form Submit               |
    | conditionType    | all                                     |
    | positiveActions  | Send Email                              |
    | positiveTemplate | Interview -2 |



Scenario: Create rule for Screening stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Screening" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Document Submit               |
    | conditionType    | all                                     |
    | positiveActions  | Send Email                              |
    | positiveTemplate | Documentation Submit Request Mail -dev |


Scenario: Create rule for Test stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Test" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Test Submit |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Test Link Mail -dev       |


Scenario: Create rule for Interview stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Interview" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Feedback Submit        |
    | conditionType    | all                              |
    | positiveActions  | Send Email                       |
    | positiveTemplate | Online Interview Invitation Mail-dev |


Scenario: Create rule for Offline Interview stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Offline Interview" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Feedback Submit           |
    | conditionType    | all                                 |
    | positiveActions  | Send Email                          |
    | positiveTemplate | Offline Interview Invitation Mail -dev |


Scenario: Create rule for Offer stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Offer" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Move Stage      |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Job Offer Mail -dev       |


Scenario: Create rule for Onboarding stage
  Given I am inside the event page for "GG Campus Drive 04080405"
  And I navigate to "Stages" tab in the event
  And I click on "Onboarding" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Not Joined      |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Job Rejection Mail-dev    |
