Feature: Air Quality History

  Scenario Outline: Go through future and past air quality data
    Given I see "Aveiro" - "Espinho"  current data
    When I click <a>  page button
    Then I should be shown different "Dados Diários" information

  Examples: Past and Future
    |      a     |
    | "Seguinte" | 
    | "Seguinte" |
    | "Anterior" |