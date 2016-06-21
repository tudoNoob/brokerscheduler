Feature: BrokerSchedule

  Scenario: Be able to schedule two brokers
    Given a broker with this name "Joaquina"
    And this broker does not want to work on "monday" in the shift "morning"
    And add broker to bulk
    Given  a broker with this name "Diego"
    And this broker does not want to work on "tuesday" in the shift "morning"
    And add broker to bulk
    When I bulk this list of brokers
    When I schedule
    Then should schedule "Joaquina" on "MONDAY" by "morning"
    Then should schedule "Diego" on "TUESDAY" by "morning"