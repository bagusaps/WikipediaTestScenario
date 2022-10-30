Feature: Search Wikipedia Feature

  Scenario: Verify search suggestion is correct
    Given open wikipedia
    Then user in wikipedia page
    When user type search 'test automation'
    Then search suggestion should contain 'test automation'

  Scenario: Verify search suggestion when change language is ID
    Given open wikipedia
    Then user in wikipedia page
    When user click and select language is 'id'
    Then verify selected language is 'id'
    When user type search 'belajar membaca'
    Then search suggestion should contain 'belajar membaca'
