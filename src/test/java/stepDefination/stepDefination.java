package stepDefination;


import Pojo.AddPlace;
import Pojo.Location;
import Resources.APIResources;
import Resources.TestBuildJava;
import Resources.Utlis;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class stepDefination extends Utlis {

    RequestSpecification res;
    static String place_id;

    ResponseSpecification resspec;
    Response response;
    TestBuildJava data=new TestBuildJava();


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with_(String name, String language ,String address) throws IOException {




                 res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));

            }



    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_Post_request(String resource,String method) throws Throwable {

      APIResources resourcesAPI=APIResources.valueOf(resource); //enum implementation
        System.out.println(resourcesAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("Post")) {
            response = res.when().post(resourcesAPI.getResource());
               //     .then().spec(resspec).extract().response();
        }
        else if (method.equalsIgnoreCase("Get"))
        {
            response = res.when().get(resourcesAPI.getResource());


        }
    }



    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code_200(int ne) throws Throwable {
      assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void status_in_response_body_is_ok(String string1, String string2) throws Throwable {


        assertEquals(getJsonPath(response,string1),string2);
    }
    @Then("verify place Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String string, String string2) throws Throwable {
         place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_Post_request(string2,"GET");
        String name=getJsonPath(response,"name");
        System.out.println(name);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException{
       res= given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
        // Write code here that turns the phrase above into concrete actions

    }


}