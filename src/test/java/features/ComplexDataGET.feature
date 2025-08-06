Feature: ComplexDataGet
  Verify complex data


@smoke
Scenario: Verify GET operation for complex data
  Given I perform authentication operation for "/auth/login" with body
    |email           |password|
    |nilson@email.com|nilson  |
  And I perform GET operation with query parameter for address "/location"
  | id|
  | 1 |
  Then I should see the street name as "123 Main Street" for the "Home" address