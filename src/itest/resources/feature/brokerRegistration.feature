Feature: Broker Registration

  Scenario: I want to register a broker

    Given a broker with this name "rafael"
    And this broker does not want to work on "monday" in the shift "not available"
    When register the broker
    Then the broker should be registered with this name rafael


  Scenario: Should throw exception when broker already exists

    Given a broker with this name "william"
    And this broker does not want to work on "monday" in the shift "not available"
    When register the broker
    When register the broker
    Then as the broker is already register should throw runtimeException
    But with error message: "Broker already exist."

  Scenario: Should throw exception for error in weekDay

    Given a broker with this name "Andreson"
    And this broker does not want to work on "segunda" in the shift "not available"
    When register the broker
    Then as the broker is already register should throw runtimeException
    But with error message: "Constrain with error."

