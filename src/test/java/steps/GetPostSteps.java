package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import models_pojo.Address;
import models_pojo.Location;
import models_pojo.LoginBody;
import models_pojo.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionv2;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetPostSteps {

    private static ResponseOptions<Response> response;
    public static String token;

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws Throwable {
        response = RestAssuredExtension.GetOpsWithToken(url,token);

    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
       //with Builder Pattern and Lombok
       // Posts posts= Posts.builder().build();
        var post=response.getBody().as(Posts.class);
        assertThat(post.getAuthor(),equalTo(authorName));


        //Without Builder Patter
       // var posts=response.getBody().as(Posts.class);
       // assertThat(posts.getAuthor(),equalTo(authorName));

        //SImple json path approach
      //  assertThat(response.getBody().jsonPath().get("author"), hasItem("Abhijit Kasana"));

    }

    @Then("I should see the author names")
    public void iShouldSeeTheAuthorNames() throws Throwable {
        BDDStyledMethod.PerformContainsCollection();
    }

    @Then("I should see the author names by path param")
    public void iShouldSeeTheAuthorNamePathParam() throws Throwable {
        BDDStyledMethod.PerformContainsCollection();
    }


    @Given("I perform POST operation for {string}")
    public void iPerformPOSTOperationFor(String arg0) {
        BDDStyledMethod.PerformPostOpsWithBodyParam();
    }

    @Given("I perform POST operation for {string} Profile page")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) throws Throwable {
        // var data = table.asMap(); //retruns single Map
        var data = table.asMaps(); //returns List<Map<String,String>>  when table has header row each map represents one row -internal gherkin logic

        var row = data.get(0); //gets the first row from gherkin table
        //Set Body
        HashMap<String, String> profileDetails = new HashMap<>();
        profileDetails.put("name", row.get("name"));
        profileDetails.put("status", row.get("status"));

        //Path Params
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("profileNo", row.get("profileNo"));

        //Upper Body
        HashMap<String, Object> body = new HashMap<>();
        body.put("profile", profileDetails);
        response = RestAssuredExtension.PostwithBody(url, body);
        System.out.println(response.toString());
    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("profile.name"),equalTo(name));// Write code here that turns the phrase above into concrete actions    throw new cucumber.api.PendingException();}

    }

    @And("I perform GET operation for posts with path param for {string}")
    public void iPerformGETOperationForPostsWithPathParamFor(String url,DataTable table) throws Throwable {
        var data=table.asList(String.class);

        var firtrowvalue =data.get(1);
        HashMap<String,String> pathparams=new HashMap<>();
        pathparams.put("id",firtrowvalue);

        System.out.println("GET URL (with path param): " + url);
        System.out.println("Path params: " + pathparams);
        response=RestAssuredExtension.GetOpsWithPathParam(url,pathparams);
        System.out.println("GET status code: " + response.getStatusCode());
        System.out.println("GET response body: " + response.getBody().asString());

    }

    @Then("^I should( not)? see the body with title as \"([^\"]*)\"$")
    public void iShouldSeeOrNotSeeTheBodyWithTitleAs(String not, String name) {
        boolean expectPresence = (not == null);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString().trim();

        if (!expectPresence && (statusCode == 404 || statusCode == 204 || responseBody.isEmpty())) {
            System.out.println("Resource not found or empty as expected. Status: " + statusCode);
        } else {
            String actualTitle = response.getBody().jsonPath().getString("title");
            if (expectPresence) {
                assertThat("Expected title to be present", actualTitle, equalTo(name));
            } else {
                assertThat("Deleted resource title should not be present", actualTitle, not(name));
            }
        }
    }


    @Given("I perform authentication operation for {string} with body")
    public void iPerformAuthenticationOperationForWithBody(String uri,DataTable table) throws Throwable {
        var data=table.asMaps();
        var nonheaderrow=data.get(0);

        System.out.println(nonheaderrow.get("email") + " plus "+ nonheaderrow.get("password"));
        //HashMap<String,Object> auth=new HashMap<>();
        //auth.put("email",nonheaderrow.get("email"));
        //auth.put("password",nonheaderrow.get("password"));

        //
        LoginBody loginBody=new LoginBody();
        loginBody.setEmail(nonheaderrow.get("email"));
        loginBody.setPassword(nonheaderrow.get("password"));


        //response=RestAssuredExtension.PostwithBody(url,auth);
        RestAssuredExtensionv2 restAssuredExtensionv2=new RestAssuredExtensionv2(uri, APIConstant.ApiMethods.POST,null);
        token=restAssuredExtensionv2.Authenticate(loginBody);
    }

    @And("I perform GET operation with query parameter for address {string}")
    public void iPerformGETOperationWithQueryParameterForAddress(String uri,DataTable table) throws Throwable {
        var data=table.asList(String.class);
        var firtrowvalue =data.get(1);

        HashMap<String,Object> queryParams=new HashMap<>();
        queryParams.put("id",firtrowvalue);
        System.out.println("GET URL (with query param): " + uri);
        System.out.println("Path params: " + queryParams);

        //response=RestAssuredExtension.GetOpsQueryParamWithToken(url,queryParams,response.getBody().jsonPath().get("access_token"));
       // System.out.println("GET status code: " + response.getStatusCode());
       // System.out.println("GET response body: " + response.getBody().asString());

       RestAssuredExtensionv2 restAssuredExtensionv2=new RestAssuredExtensionv2(uri,"GET",token);
       response=restAssuredExtensionv2.ExecuteAPIWithQueryParams(queryParams);
    }


    @Then("I should see the street name as {string} for the {string} address")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) {
        var location=response.getBody().as(Location[].class);

        Address address= location[0].getAddress().stream().filter(x->x.getType().equalsIgnoreCase(type))
                .findFirst().orElse(null);
        assertThat(address.getStreet(),equalTo(streetName));
    }

    @Then("Verify the post schema with json validation")
    public void verifyThePostSchemaWithJsonValidation() {
        var responseBody= response.getBody().asString();
        //assert
        assertThat(responseBody,matchesJsonSchemaInClasspath("post.json"));

    }
}