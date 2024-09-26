Feature: Assessment

  Scenario: Test 1
    Given User Opens the assessment page
    And Email address input, password inputs and login button are present
    Then Enter email and password

  Scenario: Test 2
    Given User Opens the assessment page
    And Assert that there are 3 values in the listgroup
    And Assert that the second list item's value is set to 'List Item 2'
    Then Assert second badge value is 6

  Scenario: Test 3
    Given User Opens the assessment page
    And Assert that 'Option 1' is the default selected value
    Then Select Option 3

  Scenario: Test 4
    Given User Opens the assessment page
    Then First button is enabled
    And Second button is disabled

  Scenario: Test 5
    Given User Opens the assessment page
    And Wait for button to be displayed and click
    Then Success message is displayed
    And Button is disabled

  Scenario: Test 6
    Given User Opens the assessment page
    Then Find the value of cell at coordinates 2,2 and aseert it is 'Ventosanzap'