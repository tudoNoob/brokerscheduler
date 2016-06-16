Feature: Find by Name a list of Brokers
  Scenario: Find brokers that has the following name "rafael"
    Given  a broker with this name "Rafael Joao"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    Given  a broker with this name "Paula Rafael"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    Given  a broker with this name "William"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    When I bulk this list of brokers
    Then return the same list that I bulk
    When I search brokers for the name "Rafael"
    Then should bring the list with only the brokers that match the name "Rafael"
