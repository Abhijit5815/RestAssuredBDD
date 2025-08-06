Feature:
  Verify different DELETE operations using Rest-assured for profile page


  Scenario: Verify DELETE  operation after POST
    Given I perform POST operation for Posts "/posts" with body
      | id | title    | author   |
      | 6  | SDET LEAD| Abhijit  |
    And I perform DELETE OPERATION for "/posts/{id}"
      |  id  |
      |  6   |
    And I perform GET operation for posts with path param for "/posts/{id}"
      |  id  |
      |  6   |
    Then I should not see the body with title as "SDET LEAD"


