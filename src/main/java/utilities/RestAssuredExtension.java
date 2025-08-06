package utilities;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


public class RestAssuredExtension {

    public static RequestSpecification Request;
    public RestAssuredExtension(){
    //Arrange
        RequestSpecBuilder builder =new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        var requestSpec=builder.build();  //return
        Request= RestAssured.given().spec(requestSpec);
    }

    //Act
    public static ResponseOptions<Response> GetOpsWithPathParam(String url, Map<String, String> pathParams) throws Throwable {

        Request.pathParams(pathParams);
        return Request.get(url);
    }

    public static ResponseOptions<Response> GetOps(String url) throws URISyntaxException {
        return Request.get(new URI(url));
    }

    public static ResponseOptions<Response> GetOpsWithToken(String url,String token) throws Throwable {
        Request.header(new Header("Authorization","Bearer "+ token));
        return Request.get(url);
    }

    public static ResponseOptions<Response> GetOpsQueryParamWithToken(String url,Map<String,String> queryParams,String token) throws Throwable {
        Request.header(new Header("Authorization","Bearer "+ token));
        Request.queryParams(queryParams);
        return Request.get(url);
    }

    public static ResponseOptions<Response> PostwithBody(String url,Map<String,Object> body) throws Throwable {
      Request.body(body);
        System.out.println(Request.body(body).toString());
      return Request.post(url);

    }

    public static ResponseOptions<Response> DeleteOpsWithPathParam(String url, Map<String, String> pathParams) throws Throwable {

        Request.pathParams(pathParams);
       return Request.delete(url);

    }

    public static ResponseOptions<Response> PutWithBodyAndPathParams(String url,Map<String,Object> body) throws Throwable {
        Request.pathParams("id",body.get("id")).body(body);
        System.out.println(Request.pathParams("id",body.get("id")).body(body).toString());
        return Request.put(url);
    }

}
