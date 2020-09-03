package com.borisenko.test.api;

import com.borisenko.test.model.ShoppingList;
import com.borisenko.test.util.PropertyLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;



public class ApiTests {

ThreadLocal<String> id= new ThreadLocal<>();

    @BeforeClass
    public void initClass(){
        RestAssured.baseURI= PropertyLoader.loadProperty("api.url");
        RestAssured.requestSpecification = given()
                .headers(
                        "Authorization",
                        "Bearer " + PropertyLoader.loadProperty("api.token"),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON);
    }

    @BeforeMethod
    public void precondition()
    {
        String randomName= UUID.randomUUID().toString();
       id.set(given().body("{\"name\": \""+randomName+"\",\"primary\": false}")
                .post("/list/v2")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .get("list.id"));
    }

    @AfterMethod
    public void postcondition()
    {

        given().pathParam("id",id.get())
                .delete("/list/v2/{id}");


    }
    // test failed because server return  http code - 400 for request. In test task condition - check http code -200
    @Test
    public void deleteShoppingList()
    {
        String queryId=id.get();
        given().pathParam("id",queryId)
                .delete("/list/v2/{id}")
                .then()
                .assertThat()
                .statusCode(200);
        ValidatableResponse response=given().pathParam("id",queryId)
                .get("/list/v2/{id}")
                .then();
        assertThat(" shopping list is exists ",response.extract().jsonPath().getString("code").compareTo ("shoppingList.notFound")==0);
        assertThat(" status code is " +response.extract().statusCode() + " , not equals 200 ",response.extract().statusCode()== 200);
    }


    @Test
    public void getShoppingList()
    {
        String queryId=id.get();
       ShoppingList shoppingList=  given().pathParam("id",queryId)
                .get("/list/v2/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().as(ShoppingList.class);
        assertThat(" id not contains "+queryId,shoppingList.getList().getId().compareTo(queryId)==0);
        assertThat(" content is not empty ",shoppingList.getContent().values().isEmpty());
    }



}
