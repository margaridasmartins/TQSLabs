Feature: Air Quality Search
 
  Scenario: Find Air Quality data for a city
    When I navigate to "http://127.0.0.1:4200"
    And I choose "Braga" followed by "Barcelos"
    And I click "Pesquisar" button
    Then I should be shown "Dados Atuais" and "Dados Diários" information

  