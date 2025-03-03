Feature: Car Details Validation

  Scenario: Validate all car registration details from input file
    Given I have a file named "car_input-v6.txt" with vehicle registration numbers
    And I am on the car details validation home page
    When I search each vehicle registration on car validation website
    Then I should see all vehicle details matched correctly

  Scenario: Validate car registration details for invalid registration number
    Given I have a file named "invalid-car-inputs.txt" with invalid vehicle registration numbers
    And I am on the car details validation home page
    When I search each vehicle registration on car validation website
    Then I should see vehicle details are not found