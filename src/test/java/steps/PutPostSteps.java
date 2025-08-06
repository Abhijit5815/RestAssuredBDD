package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.stream.Collectors;

public class PutPostSteps {

    public ResponseOptions<Response> response;

    @And("I perform PUT OPERATION for {string}")
    public void iPerformPUTOPERATIONFor(String url, DataTable table) throws Throwable{
        var data = table.asMaps(); //returns List<Map<String,String>>  when table has header row each map represents one row -internal gherkin logic

        var row = data.get(0); //gets the first row from gherkin table or use getFirst()
        String logMap= row.entrySet().stream().map(entry->entry.getKey() + "=" +entry.getValue()).collect(Collectors.joining(", "));
        System.out.println(logMap);

        //Set Body
        HashMap<String, Object> putDetailsbody = new HashMap<>();
        putDetailsbody.put("id", row.get("id"));
        putDetailsbody.put("title", row.get("title"));
        putDetailsbody.put("author", row.get("author"));



        response = RestAssuredExtension.PutWithBodyAndPathParams(url,putDetailsbody);
        System.out.println("Respone is "+response.toString());

    }
}
