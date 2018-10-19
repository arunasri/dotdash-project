package com.qa.dotdash.tests;

import org.junit.Before;

public class BaseTest {
  String baseURL = System.getProperty("base.url");

  @Before
  public void setUp() {
    if (baseURL == null) {
      baseURL = "http://192.168.64.2/";
    }
  }
}
