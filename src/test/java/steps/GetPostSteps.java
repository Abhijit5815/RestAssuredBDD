package steps;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;

public class GetPostSteps {
    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws Throwable {
    given().contentType(ContentType.JSON);

    }

    @And("I perform GET for the post number {string}")
    public void iPerformGETForThePostNumber(String postNumber) throws Throwable {
        when().get(String.format("http://localhost:3000/posts/%s",postNumber))
                .then().body("author",is("Abhijit Kasana"));
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String arg0) throws Throwable {

    }
}
