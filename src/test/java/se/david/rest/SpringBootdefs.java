package se.david.rest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@ContextConfiguration
public class SpringBootdefs {
    @LocalServerPort
    private int port;
    private ValidatableResponse response;

    @Before
    public void before() {
        RestAssured.port = port;
    }

    @When("^calling endpoint (.*)$")
    public void callRest(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then();
    }

    @Then("^response status should be (\\d*)$")
    public void checkResult(int expectedStatusCode) {
        response.statusCode(expectedStatusCode);
    }

    @Then("^result should contain \"(.*)\"$")
    public void checkResultBody(String expectedBody) {
        response.body(CoreMatchers.equalTo(expectedBody));
    }
}
