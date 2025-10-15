Feature: Wompi Payment Integration Tests
  As a merchant using Wompi payment platform
  I want to process PSE payments successfully
  So that I can receive payments from customers

  Background:
    Given the Wompi API is available
    And I have valid merchant credentials

  @positive @pse-payment
  Scenario: Successful PSE payment transaction
    Given I have valid PSE payment data
    When I create a PSE payment transaction
    Then the transaction should be approved
    And I should receive a transaction ID
    And the transaction status should be "APPROVED"

  @negative @invalid-bank-data
  Scenario: PSE payment with invalid bank data
    Given I have invalid PSE bank data
    When I create a PSE payment transaction
    Then the transaction should be rejected
    And I should receive an error message
    And the transaction status should be "DECLINED"

  @negative @insufficient-funds
  Scenario: PSE payment with insufficient funds
    Given I have PSE payment data with insufficient funds
    When I create a PSE payment transaction
    Then the transaction should be rejected
    And I should receive an insufficient funds error
    And the transaction status should be "DECLINED"

  @negative @timeout
  Scenario: PSE payment transaction timeout
    Given I have valid PSE payment data
    When I create a PSE payment transaction
    And the user does not complete authentication within timeout period
    Then the transaction should expire
    And I should receive a timeout error
    And the transaction status should be "EXPIRED"

  @negative @invalid-merchant
  Scenario: PSE payment with invalid merchant credentials
    Given I have invalid merchant credentials
    When I create a PSE payment transaction
    Then the API should return authentication error
    And I should receive an unauthorized error message

  @positive @transaction-status-check
  Scenario: Check transaction status after successful payment
    Given I have a successful PSE payment transaction
    When I query the transaction status
    Then I should receive the current transaction status
    And the status should be "APPROVED"
