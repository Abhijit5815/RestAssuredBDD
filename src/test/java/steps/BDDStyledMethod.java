package steps;

import io.restassured.http.ContentType;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BDDStyledMethod {

    public static  void SimpleGETPost(String postNumber){
        given().contentType(ContentType.JSON).
        when().get(String.format("http://localhost:3000/posts/%s",postNumber))
                .then().body("author",is("Abhijit Kasana"));
    }

    public static  void PerformContainsCollection(){
        given().contentType(ContentType.JSON).
                when().get("http://localhost:3000/posts/")
                .then().body("author",containsInAnyOrder("Abhijit Kasana","Anwesha Kasana")).statusCode(200);
    }

    public static  void PerformPathParameter(){
        given()
                .contentType(ContentType.JSON).
        with().
         pathParams("post",2).
        when().
                get("http://localhost:3000/posts/{post}")
        .then().body("author",containsString("Anwesha Kasana")).statusCode(200);
    }

    public static  void PerformQueryParameter(){
        given()
                .contentType(ContentType.JSON).
        with().
                queryParam("id",2).
        when().
                get("http://localhost:3000/posts")
        .then().body("author",containsString("Anwesha Kasana")).statusCode(200);
    }

    public static  void PerformPostOpsWithBodyParam(){
        HashMap<String,String> postContent=new HashMap<>();
        postContent.put("id","4");
        postContent.put("title","freefire");
        postContent.put("author","Gublu");

        given()
                .contentType(ContentType.JSON).
        with().
                body(postContent).
        when().
                post("http://localhost:3000/posts")
                .then().body("author",containsString("Gublu")).statusCode(201);
    }


}
