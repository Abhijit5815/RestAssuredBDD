Feature:
  Verify different PUT operations using Rest-assured for post page

  Scenario: Verify PUT  operation for a POST
Given I perform POST operation for Posts "/posts" with body
| id | title    | author   |
| 5  | SDET     | Abhijit  |
And I perform PUT OPERATION for "/posts/{id}"
| id | title    | author   |
| 5  |LEAD SDET | Abhijit  |
And I perform GET operation for posts with path param for "/posts/{id}"
|  id  |
|  5   |
Then I should see the body with title as "LEAD SDET"
