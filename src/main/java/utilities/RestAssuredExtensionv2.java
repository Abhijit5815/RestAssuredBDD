package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionv2 {

    //Properties
    private RequestSpecBuilder builder=new RequestSpecBuilder();
    private String method;
    private String url;

   //Constructor

    /**
     * This constructor to pass the initial settings for the following methods
     * @param uri ="path of endpoint without core URL"
     * @param method="GET","PUT"etc
     * @param token="Authorization toke"
     */
   public RestAssuredExtensionv2(String uri,String method,String token){

       //Formatulate the API url
       this.url="http://localhost:3000"+uri;
       this.method=method;

       if(token!=null){
           builder.addHeader("Authorization","Bearer "+ token);
       }

   }

    /**
     *ExecuteAPI to execute the API for GET,POST,DELETE
     * @return ResponseOptions<response></>
     */
   private ResponseOptions<Response> ExecuteAPI(){
       RequestSpecification requestSpecification= builder.build();
       RequestSpecification request=RestAssured.given();
       request.contentType(ContentType.JSON);
       request.spec(requestSpecification);

       if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
           return request.post(this.url);
       else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
           return request.delete(this.url);
       else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
           return request.get(this.url);
       return null;
   }


    /**
     * Authenticate to get the token variable
     * @param body Map<String,Object></>
     * @return string token
     */
   public String Authenticate(Object body){
        builder.setBody(body);
       return ExecuteAPI().getBody().jsonPath().get("access_token");
   }

    /**
     * Method to executAPi with Query params
     * @param queryPath
     * @return Response
     */
    public ResponseOptions<Response> ExecuteAPIWithQueryParams(Map<String,Object> queryPath){
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    /**
     * Method to executAPi with Path params
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParams(Map<String,Object> pathParams){
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * Method to executeAPI with Body and Path Params
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteAPIWithPathParamsAndBody(Map<String,Object> pathParams,Map<String,Object> body){
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }
}
