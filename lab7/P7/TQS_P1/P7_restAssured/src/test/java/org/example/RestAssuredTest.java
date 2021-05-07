package org.example;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class RestAssuredTest {

    @Test
    public void givenUrl_checkAllToDosAvailable() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }

    @Test
    void givenUrl_checkTitle4() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).assertThat()
                .body("title", Matchers.containsString("et porro tempora"));
    }

    @Test
    void givenUrl_whenListingTodos_Get198and199() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).assertThat()
                .body("id", hasItems("198", "199"));
    }

}