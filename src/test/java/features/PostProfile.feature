Feature:
  Verify different POST operations using Rest-assured for profile page

  Scenario: Verify Post operation for Profiles
    Given I perform POST operation for Profiles "/posts" with body
      | profileNo | name    | status   |
      | 6         | Jordan  | active   |
    Then I should see the body has name as "Jordan"



  Scenario: Verify Post operation for Posts
    Given I perform POST operation for Posts "/posts" with body
      | id | title    | author   |
      | 6  | QA       | Donald   |
    Then I should see the body has name as "Donald"

