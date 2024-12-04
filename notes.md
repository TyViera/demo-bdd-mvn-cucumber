## Notes

TDD - Test-driven development -
BDD - Behavior-driven development

## Case

As bank client

I want to check if one transfer operation is fraudulent or not to transfer money from one source account to another

Then the transfer has to fullfil the next conditions:

1. Open date of source account...
2. Check average transfer amount...
3. Operation date time...

## Scenarios

### Scenario 1

-- Header --

When the opening date of the source account is today

Then the operation must be marked as fraudulent

### Scenario 2

-- Header --

When the transfered amount is greater or equal than the double of the weekly average of the source account

Then the operation must be marked as fraudulent

### Scenario 3

-- Header --

When the operation is done between 00:00 and 04:00

Then the operation must be marked as fraudulent

### Scenario 4

-- Header --

When the opening date of the source account is <date> ago

And the opening date of the target account is <date> ago

And the transfer amount is less than the double's weekly average

And the operation is done at 23:24

Then the operation must be marked as safe

