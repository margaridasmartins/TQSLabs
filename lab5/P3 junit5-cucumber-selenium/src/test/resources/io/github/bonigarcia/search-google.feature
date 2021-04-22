Feature: Flights search in blazedemo
 
  Scenario: Find flights from a city to another
    When I navigate to "https://blazedemo.com/"
    And I choose "Boston" followed by "Rome"
    And I click "Find Flights" button
    Then I should be shown "Flights from Boston to Rome" page