Feature: Event Info - Navigation and Stage Management

@tabnav
Scenario: Create Screening stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Screening" stage named "Screening" with dropdown "Document form"
  Then I should see a success message for stage creation
@tabnav
Scenario: Create Test stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Test" stage named "Test" with dropdown "Aptitude Assessment" and link "Test 1"
  Then I should see a success message for stage creation

  @tabnav
Scenario: Create Interview stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Interview" stage named "Technical Round" with dropdown "Feed back form" and link "Interview -1"
  Then I should see a success message for stage creation


  @tabnav
Scenario: Create Offline Interview stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Offline Interview" stage named "Technical Round" with dropdown "Feed back form" and link "Offline interview"
  Then I should see a success message for stage creation


  @tabnav
Scenario: Create Offer stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Offer" stage named "offer_form" with dropdown "offer_form" and link "Job Offer Mail"
  Then I should see a success message for stage creation


  @tabnav
Scenario: Create Onboarding stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Onboarding" stage named "Onboarding"
  Then I should see a success message for stage creation



  @tabnav
Scenario: Create Others stage
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I create a "Others" stage named "Others"
  Then I should see a success message for stage creation

@tabnav1
Scenario: Checking navigation to all tabs inside event details
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Candidates" tab in the event
  And I navigate to "Event Details" tab in the event
  And I navigate to "Stages" tab in the event
  And I navigate to "Interview" tab in the event


@tabnav2
Scenario: Checking navigation to all tabs inside event details
  Given I am inside the event page for "July Campus Drive 13"
  And I navigate to "Stages" tab in the event
  And I select "100" items per page in the Stages candidate list


 @selectCandidates
  Scenario: Select a candidate by email
  Given I am inside the event page for "July Campus Drive 9"
  And I navigate to "Stages" tab in the event
  And I select "100" items per page in the Stages candidate list
  When I search and select candidate with email "naveen@examly.in"
  # Then the candidate should be selected

  @selectCandidates1
  Scenario: Select all candidates in the table
    Given I am on the candidate table page
    When I select all candidates in the table
    # Then all candidates should be selected