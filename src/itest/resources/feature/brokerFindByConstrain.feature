Feature: Find a list of brokers by constrain
  Scenario: Find a list of Broker by constrain
    Given  a broker with this name "Juca"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    Given  a broker with this name "Tita"
    And this broker does not want to work on "friday" in the shift "morning"
    And add broker to bulk
    When I bulk this list of brokers
    When I filter by constrain "monday"
    Then all the brokers in the list should have the constrian "monday"