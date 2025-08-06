Feature: Event Info - Navigation and Stage Management

@createStage
Scenario: Create Screening stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Screening" stage named "Screening" with dropdown "Document form"
  Then I should see a success message for stage creation
@createStage
Scenario: Create Test stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Test" stage named "Test" with dropdown "Aptitude Assessment" and link "Test 1"
  Then I should see a success message for stage creation

  @createStage
Scenario: Create Interview stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Interview" stage named "Interview" with dropdown "Feed back form" and link "Interview -1"
  Then I should see a success message for stage creation


  @createStage
Scenario: Create Offline Interview stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Offline Interview" stage named "Offline Interview" with dropdown "Feed back form" and link "Offline interview"
  Then I should see a success message for stage creation


  @createStage
Scenario: Create Offer stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Offer" stage named "Offer" with dropdown "offer_form" and link "Job Offer Mail"
  Then I should see a success message for stage creation


  @createStage
Scenario: Create Onboarding stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Onboarding" stage named "Onboarding"
  Then I should see a success message for stage creation



  @createStage
Scenario: Create Others stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I create a "Others" stage named "Others"
  Then I should see a success message for stage creation

@tabnav
Scenario: Checking navigation to all tabs inside event details
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Candidates" tab in the event
  # And I navigate to "Event Details" tab in the event
  # And I navigate to "Stages" tab in the event
  # And I navigate to "Interview" tab in the event


@rowCount
Scenario: Checking no of rows in table
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I select "100" items per page in the Stages candidate list


 @selectCandidates
  Scenario: Select a candidate by email
  Given I am inside the event page for "July Campus Drive 9"
  And I navigate to "Stages" tab in the event
  And I select "100" items per page in the Stages candidate list
  When I search and select candidate with email "naveen@examly.in"
  # When I select all candidates in the table

@stageRename
Scenario: Checking navigation to all tabs inside event details
  Given I am inside the event page for "July Campus Drive 9"
  And I navigate to "Stages" tab in the event
  And I click on "ssss" stage 
  And In "ssss" stage I click on "Rename" option
  And I change the name to "dddd"


@stageAction
Scenario: Perform a stage action - send mail
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Eligibility Check" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "send mail" with the following parameters:
    | template | User Welcome Mail |
  Then I should see a success message for stage action


@stageAction2
Scenario: Perform a stage action - move stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Offline Interview" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "move stage" with the following parameters:
    | targetStage | Offer     |
    | remarks     | Promoted to next round |
  Then I should see a success message for stage action


@stageAction3
Scenario: Perform a stage action - send documentation link
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Screening" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "send documentation link" with the following parameters:
    | jobLocation | Coimbatore              |
    | jobRole     | QA Engineer          |
    | docPocRole  | Super Admin           |
    | docPoc      | Muniyappan Mani             |
    | deadline    | 2 Hours           |
    | template    | Documentation Submit Request Mail -dev   |
  Then I should see a success message for stage action



@stageAction4
Scenario: Perform a stage action - send interview link
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Interview" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "send interview link" with the following parameters:
  | date      | 02-08-2025 |
  | startTime | 10:30         |
  | duration  | 1h 0m        |
  Then I should see a success message for stage action


@stageAction5
Scenario: Perform a stage action - send interview venue
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Offline Interview" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "send interview venue" with the following parameters:
  | date      | 02-08-2025 |
  | startTime | 02:00       |
  | duration  | 30m         |
  | venue     | Room 302    |
  Then I should see a success message for stage action


@stageAction6
Scenario: Perform a stage action - send offer
  Given I am inside the event page for "GG Campus Drive 280804"
  And I navigate to "Stages" tab in the event
  And I click on "Offer" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "send offer" with the following parameters:
    | jobLocation   | Coimbatore              |
    | jobRole       | QA Engineer                   |
    | offerDeadline | 6 Hours             |
    | template      | Job Offer Mail -dev      |
  Then I should see a success message for stage action


@stageAction7
Scenario: Perform a stage action - Send Test Link
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Test" stage
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "Send Test Link" without parameters
  Then I should see a success message for stage action


@stageAction8
Scenario: Perform a stage action - waitlist candidate
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Offer" stage 
  And I search and select candidate with email "naveen@examly.in"
  When I perform stage action "Offer Rejected" with the following parameters:
    | remarks | Candidate moved to Future Candidate due to role freeze |
  Then I should see a success message for stage action


@ruleCreation1
Scenario: Create a rule with for all condition
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Eligibility Check" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when          | Candidate Form Submit       |
    | conditionType | all                         |
    | positiveActions       | Send Email                  |
    | positiveTemplate     | Interview -2                |
  Then I should see a success message for rule creation


@ruleCreation
Scenario: Create a rule with matching condition and single actions
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Eligibility Check" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Form Submit                    |
    | conditionType    | matching                                 |
    | field            | Email ID                                 |
    | operator         | ==                                       |
    | value            | naveen@examly.in                         |
    | positiveActions  | Send Email                               |
    | positiveTemplate | Documentation Submit Request Mail -dev   |
    | negativeActions  | Send Email                               |
    | negativeTemplate | Job Rejection Mail-dev                   |
  Then I should see a success message for rule creation


@ruleCreation
  Scenario: Create a rule with matching condition and multiple actions and single template
    Given I am inside the event page for "GG Campus Drive 040804w"
    And I navigate to "Stages" tab in the event
    And I click on "Eligibility Check" stage
    And I click on "Rules" tab button
    When I create a rule with the following parameters:
      | when             | Candidate Form Submit                    |
      | conditionType    | matching                                 |
      | field            | Email ID                                 |
      | operator         | ==                                       |
      | value            | naveen@examly.in                         |
      | positiveActions  | Send Email, Shortlist Candidate          |
      | positiveTemplate | Documentation Submit Request Mail -dev  |
      | negativeActions  | Send Email, Reject Candidate             |
      | negativeTemplate | Job Rejection Mail-dev                   |
    Then I should see a success message for rule creation


@ruleCreation
  Scenario: Create a rule with matching condition and multiple actions and multiple template
    Given I am inside the event page for "GG Campus Drive 040804w"
    And I navigate to "Stages" tab in the event
    And I click on "Eligibility Check" stage
    And I click on "Rules" tab button
     When I create a rule with the following parameters:
    | when             | Candidate Form Submit                          |
    | conditionType    | matching                                       |
    | field            | Email ID                                       |
    | operator         | ==                                             |
    | value            | naveen@examly.in                               |
    | positiveActions  | Send Email, Send Email                         |
    | positiveTemplate | Documentation Submit Request Mail -dev, Documentation Submit Request Mail -dev |
    | negativeActions  | Send Email, Send Email                         |
    | negativeTemplate | Job Rejection Mail-dev, Job Rejection Mail-dev |
     Then I should see a success message for rule creation











@ruleCreation
Scenario: Create rule for Screening stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Screening" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Document Submit               |
    | conditionType    | all                                     |
    | positiveActions  | Send Email                              |
    | positiveTemplate | Documentation Submit Request Mail -dev |
  Then I should see a success message for rule creation

@ruleCreation
Scenario: Create rule for Test stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Test" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Document Submit |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Test Link Mail -dev       |
  Then I should see a success message for rule creation

@ruleCreation
Scenario: Create rule for Interview stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Interview" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Feedback Submit        |
    | conditionType    | all                              |
    | positiveActions  | Send Email                       |
    | positiveTemplate | Online Interview Invitation Mail-dev |
  Then I should see a success message for rule creation

@ruleCreation
Scenario: Create rule for Offline Interview stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Offline Interview" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Feedback Submit           |
    | conditionType    | all                                 |
    | positiveActions  | Send Email                          |
    | positiveTemplate | Offline Interview Invitation Mail -dev |
  Then I should see a success message for rule creation

@ruleCreation
Scenario: Create rule for Offer stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Offer" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Move Stage      |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Job Offer Mail -dev       |
  Then I should see a success message for rule creation

@ruleCreation
Scenario: Create rule for Onboarding stage
  Given I am inside the event page for "GG Campus Drive 040804w"
  And I navigate to "Stages" tab in the event
  And I click on "Onboarding" stage
  And I click on "Rules" tab button
  When I create a rule with the following parameters:
    | when             | Candidate Not Joined      |
    | conditionType    | all                       |
    | positiveActions  | Send Email                |
    | positiveTemplate | Job Rejection Mail-dev    |
  Then I should see a success message for rule creation



@eventRegsiterByUrl
Scenario Outline: Enroll candidate by URL
  Given I am inside the event page for "GG Campus Drive 0408202600"
  And I navigate to "Candidates" tab in the event
  And I copy the registration URL
  And I open the "URL" in new tab
  And I fill the email ID with "naveeniamneo112@gmail.com"
  And I confirm the email ID with "naveeniamneo112@gmail.com"
  And I click on verify button
  And I enter OTP from mail
  And I click Verify submit button
  And I select gender as "Male"
  And I enter registration number as "ddfd"
  And I enter company name as "name"
  And Click Submit