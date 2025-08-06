package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.util.HashMap;

public class PostProfileSteps {

    private static ResponseOptions<Response> response;

    @Given("I perform POST operation for Profiles {string} with body")
    public void iPerformPOSTOperationForProfilesWithBody(String url,DataTable table) throws Throwable {
        // var data = table.asMap(); //retruns single Map
        var data = table.asMaps(); //returns List<Map<String,String>>  when table has header row each map represents one row -internal gherkin logic

        var row = data.getFirst(); //gets the first row from gherkin table
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

    @Given("I perform POST operation for Posts {string} with body")
    public void iPerformPOSTOperationForPostsWithBody(String url,DataTable table) throws Throwable {
        var data = table.asMaps(); //returns List<Map<String,String>>  when table has header row each map represents one row -internal gherkin logic

        var row = data.get(0); //gets the first row from gherkin table or use getFirst()
        //Set Body
        HashMap<String, Object> postDetailsbody = new HashMap<>();
        postDetailsbody.put("id", row.get("id"));
        postDetailsbody.put("title", row.get("title"));
        postDetailsbody.put("author", row.get("author"));

        response = RestAssuredExtension.PostwithBody(url, postDetailsbody);
        System.out.println(response.toString());

    }
}
