package com.qa.dotdash.tests;

import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITest extends BaseTest {
  String API_URL = "dotdash/fake-api-call.php";

  @Test
  public void testResponseCode() {
    RestAssured.given().when().get(baseURL + API_URL).then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  public void getTaskNames() throws ParseException {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(resp.asString());
    JSONArray array = (JSONArray) obj;
    for (int i = 0; i < array.size(); i++) {
      System.out.println(((JSONObject) array.get(i)).get("task name"));
    }
  }

  @Test
  public void tasksWithNoCategorys() throws ParseException {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    JSONParser parser = new JSONParser();
    System.out.println(resp.asString());
    Object obj = parser.parse(resp.asString());
    JSONArray array = (JSONArray) obj;
    int noCategory = 0;
    for (int i = 0; i < array.size(); i++) {
      JSONObject todo = (JSONObject) array.get(i);
      String category = (String) todo.get("category");
      if (category.isEmpty()) {
        noCategory++;
      }
    }
    System.out.println("items with no categories:" + noCategory);
  }

  @Test
  public void countNumberOfTodos() throws ParseException {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(resp.asString());
    JSONArray array = (JSONArray) obj;

    System.out.println("number of items:" + array.size());
  }
}
