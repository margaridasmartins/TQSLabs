Feature: Basic Arithmetic

  Background: A Calculator
    Given a calculator I just turned on

  Scenario: Addition
    When I add 4 and 5
    Then the result is 9

  Scenario: Substraction
    When I substract 7 to 2 
    Then the result is 5

  Scenario: Multiplication
    When I multiply 3 and 4
    Then the result is 12

  Scenario: Division
    When I divide 20 to 5
    Then the result is 4

  Scenario Outline: Several additions
    When I add <a> and <b>
    Then the result is <c>

  Examples: Single digits
    | a | b | c  |
    | 1 | 2 | 3  |
    | 3 | 7 | 10 |

  Scenario Outline: Several multiplications
    When I multiply <d> and <e>
    Then the result is <f>

  Examples: Single digits
    | d | e | f  |
    | 1 | 2 | 2  |
    | 0 | 7 | 0  |
    | 4 | 7 | 28 |
  