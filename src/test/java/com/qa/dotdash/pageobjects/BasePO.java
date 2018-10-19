package com.qa.dotdash.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePO {
  // Constructor
  public WebDriver driver;

  public BasePO(WebDriver driver) {
    // Initializing elements
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }
}
