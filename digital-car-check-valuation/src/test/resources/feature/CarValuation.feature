Feature: Car Valuation check

  Scenario: Validate all car registrations less than 3000 pounds
    Given I have a car with vehicle registration number "AD58 VNF"
    And I am on the home page
    When I search each vehicle registration on car valuation website
    Then I should see vehicle valuation is less than 3000 pounds
