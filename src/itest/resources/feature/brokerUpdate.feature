Feature: Broker Update

  Scenario: I want to update a broker

    Given a broker with this name "william"
    When register the broker
    Given a broker with this name "rafael"
    When I update the broker with the id "1"
    Then I will have a broker with the name "rafael"

  Scenario: I want to update a broker but the brokers doesn't exist.

    Given a broker with this name "rafael"
    When I update the broker with the id "1"
    Then I will have a broker with the name "rafael"