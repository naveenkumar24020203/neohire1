@smoke
Feature: Smoke Test for Role creation, Event creation, Stage Creation and Candidate Invite

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
    | TTT Engineer   | Full Time | Hybrid   | India   | Tamil Nadu | Coimbatore  | uuhhd        | GG Campus Drive 0508202503 | 2026-08-28 12:30 PM  | 2026-08-29 12:00 PM  | 2026-08-26 12:00 PM | 2026-08-27 12:00 PM | TTT Engineer | 2          | 300000    | 600000    | In-Campus | Technical     | Test Link            | demo Copy        | AAA COLLEGE OF ENGINEERING AND TECHNOLOGY, SIR VISHVESHWARIAH INSTITUE OF SCIENCE AND TECHNOLOGY | AddreTTTTTTTTTs              |

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
    And I select Event name as "<eventTitle>"
    And I select Source type as "<sourceType>"
    And I select Source name as "<sourceName>"
    Then I click Send Invite button

    Examples:
      | inviteType    | firstName    | lastName | emailId               | gender | dob         | mobile     | eventCategory | eventTitle               | sourceType | sourceName |
      | Single-Invite | Naveen Kumar | S        | naveen+02@examly.in   | Male   | 24-02-2003  | 8855885548 | Technical     | GG Campus Drive 0508202503 | Data       | Linkedin    |

  Scenario Outline: Create "<stageType>" stage
    Given I am inside the event page for "<eventTitle>"
    And I navigate to "Stages" tab in the event
    And I create a "<stageType>" stage named "<stageName>" <stageDropdown> <stageLink>
    Then I should see a success message for stage creation

    Examples:
      | eventTitle              | stageType         | stageName         | stageDropdown         | stageLink                   |
      | GG Campus Drive 0508202503 | Screening         | Screening         | with dropdown "Document form"             |                             |
      | GG Campus Drive 0508202503 | Test              | Test              | with dropdown "Aptitude Assessment"        | and link "Test 1"           |
      | GG Campus Drive 0508202503 | Interview         | Interview         | with dropdown "Feed back form"             | and link "Interview -1"     |
      | GG Campus Drive 0508202503 | Offline Interview | Offline Interview | with dropdown "Feed back form"             | and link "Offline interview"|
      | GG Campus Drive 0508202503 | Offer             | Offer             | with dropdown "offer_form"                 | and link "Job Offer Mail"   |
      | GG Campus Drive 0508202503 | Onboarding        | Onboarding        |                                              |                             |
      | GG Campus Drive 0508202503 | Others            | Others            |                                              |                             |

  Scenario Outline: Create rule for "<stageType>" stage
    Given I am inside the event page for "<eventTitle>"
    And I navigate to "Stages" tab in the event
    And I click on "<stageType>" stage
    And I click on "Rules" tab button
    When I create a rule with the following parameters:
      | when             | <whenCondition>             |
      | conditionType    | <conditionType>             |
      | positiveActions  | <positiveActions>           |
      | positiveTemplate | <positiveTemplate>          |

    Examples:
      | eventTitle              | stageType         | whenCondition                 | conditionType | positiveActions | positiveTemplate                        |
      | GG Campus Drive 0508202503 | Eligibility Check | Candidate Form Submit         | all           | Send Email      | Interview -2                            |
      | GG Campus Drive 0508202503 | Screening         | Candidate Document Submit     | all           | Send Email      | Documentation Submit Request Mail -dev |
      | GG Campus Drive 0508202503 | Test              | Candidate Test Submit         | all           | Send Email      | Test Link Mail -dev                     |
      | GG Campus Drive 0508202503 | Interview         | Candidate Feedback Submit     | all           | Send Email      | Online Interview Invitation Mail-dev   |
      | GG Campus Drive 0508202503 | Offline Interview | Candidate Feedback Submit     | all           | Send Email      | Offline Interview Invitation Mail -dev |
      | GG Campus Drive 0508202503 | Offer             | Candidate Move Stage          | all           | Send Email      | Job Offer Mail -dev                     |
      | GG Campus Drive 0508202503 | Onboarding        | Candidate Not Joined          | all           | Send Email      | Job Rejection Mail-dev                  |
