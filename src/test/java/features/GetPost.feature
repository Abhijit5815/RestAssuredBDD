Feature:
  Verify different GET POST operations using Rest-assured

  Scenario: Verify one author of the post
     Given I perform GET operation for "/posts"
    Then I should see the author name as "Abhijit Kasana"


  Scenario: Verify all author of the posts
    Given I perform GET operation for "/post"
    Then I should see the author names


  Scenario: Verify author of the posts with path param
    Given I perform GET operation for "/post"
    Then I should see the author names by path param


  Scenario: Verify Post operation of a new post
    Given I perform POST operation for "/post"

  Scenario: Verify Post operation for Profile
    Given I perform POST operation for "/profile" Profile page
      | profileNo | name    | status   |
      | 6         | Jordan  | active   |
    Then I should see the body has name as "Jordan"

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
    |email           |password|
    |nilson@email.com|nilson  |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Abhijit Kasana"


  @smoke
  Scenario: Verify GET operation with bearer authentication token json schema validation
    Given I perform authentication operation for "/auth/login" with body
      |email           |password|
      |nilson@email.com|nilson  |
    Given I perform GET operation for "/posts/1"
    Then Verify the post schema with json validation
