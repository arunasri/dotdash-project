package com.qa.dotdash.tests;

import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class APITest extends BaseTest {
  String API_URL = "dotdash/fake-api-call.php";

  @Test
  public void testResponseCode() {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    Assert.assertEquals(200, resp.getStatusCode());
  }

  @Test
  public void getTaskNames() throws ParseException {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(resp.asString());
    JSONArray todos = (JSONArray) obj;
    for (int i = 0; i < todos.size(); i++) {
      System.out.println(((JSONObject) todos.get(i)).get("task name"));
    }
  }

  @Test
  public void tasksWithNoCategorys() throws ParseException {
    Response resp = RestAssured.get("http://192.168.64.2/" + API_URL);
    JSONParser parser = new JSONParser();
    System.out.println(resp.asString());
    Object obj = parser.parse(resp.asString());
    JSONArray todos = (JSONArray) obj;
    int noCategory = 0;
    for (int i = 0; i < todos.size(); i++) {
      JSONObject todo = (JSONObject) todos.get(i);
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
    JSONArray todos = (JSONArray) obj;

    System.out.println("number of items:" + todos.size());
  }
}
