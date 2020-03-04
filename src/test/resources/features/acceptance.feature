Feature: Reporting API Acceptance Criteria Test

  Scenario: Should login
    Given I have a user credentials
    When I request to login with the credentials
    Then I should see the authorization token

  Scenario: Should get transactions report
    Given I have transactions in repository
    When I request for report
    Then I should see transactions report

  Scenario: Should get transaction list
    Given I have transactions in repository
    When I request for transaction list
    Then I should see transaction list

  Scenario: Should get transaction
    Given I have transactions in repository
    When I request for a transaction with transaction ID
    Then I should see the transaction

  Scenario: Should get customer data
    Given I have transactions in repository
    When I request to get customer data from repository with transaction ID
    Then I should see the customer data

