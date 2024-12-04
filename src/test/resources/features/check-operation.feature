Feature: Check fraudulent operations
  As bank client
  I want to check if one transfer operation is fraudulent or not to transfer money from one source account to another
  the transfer has to fullfil the next conditions:
    1. Open date of source account...
    2. Check average transfer amount...
    3. Operation date time...

  Scenario: Making operation with invalid opening date - operation invalid
    Given the opening date of the source account is today
    When the user check if one operation is fraudulent
    Then the operation must be marked as fraudulent

  Scenario: Making operation with invalid amount transfer - operation invalid
    Given the weekly average amount of the account "9876-9876-9876-9876" is 50 with opening date "2023-10-08T00:00:00"
    And the opening date of the account "5555-5555-5555-5555" is "2023-02-10T00:00:00"
    When the user check if one operation is fraudulent with source account "9876-9876-9876-9876" and target account "5555-5555-5555-5555" and amount 100
    Then the operation must be marked as fraudulent