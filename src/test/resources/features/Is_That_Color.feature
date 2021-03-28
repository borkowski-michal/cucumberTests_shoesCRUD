# new feature
# Tags: optional
    
Feature: Is Shoes are red ?
  Customer wants to know what color is shoes?

  Scenario Outline: It is red or not
    Given this is "<color>"
    When I ask are you sure I's red
    Then I should be told "<answer>"

    Examples:
      | color     | answer |
      | red       | Yes    |
      | black     | Nope   |
      | white     | Nope   |