package com.qa.dotdash.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DuplictePO extends BasePO {

  // Constructor
  public DuplictePO(WebDriver driver) {
    super(driver);
  }

  @FindBy(how = How.XPATH, using = "//*[text()='Sorry that TODO item already exists. ']")
  public WebElement errorMessage;

  @FindBy(how = How.XPATH, using = "//a[text()='Back']")
  public WebElement backLink;
}
