Feature: Broker Registration

  Scenario: I want to register a broker
    Given a broker with this name "william"
    When this broker does not want to work on "monday" in the shift "not available"
    Then the broker should be registered with this name william