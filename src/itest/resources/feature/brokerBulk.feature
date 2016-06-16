Feature: Bulk Broker

  Scenario: Bulk a broker list
    Given  a broker with this name "Maria"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    Given  a broker with this name "Paula"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    When I bulk this list of brokers
    Then return the same list that I bulk