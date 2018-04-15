Feature: Test spring boot integrations

  Scenario: Do the stuff
    When calling endpoint /ping/dinky
    Then response status should be 200
    Then result should contain "PONG: dinky"
