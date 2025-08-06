package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.util.HashMap;

public class DeletePosts {

        private static ResponseOptions<Response> response;

        @And("I perform DELETE OPERATION for {string}")
        public void iPerformDELETEOPERATIONFor(String url, DataTable table) throws Throwable {
          var data=table.asList(String.class);

          var postId=data.get(1);  //asList doesnot gets header row as well

            HashMap<String,String> pathparams=new HashMap<>();
            pathparams.put("id",postId);

            System.out.println("DELETE URL (with path param): " + url);
            System.out.println("Path params: " + pathparams);
            response = RestAssuredExtension.DeleteOpsWithPathParam(url, pathparams);
            System.out.println("DELETE status code: " + response.getStatusCode());
            System.out.println("DELETE response body: " + response.getBody().asString());

        }


    }


