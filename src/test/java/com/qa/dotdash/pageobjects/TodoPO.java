package com.qa.dotdash.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TodoPO extends BasePO {

  public TodoPO(WebDriver driver) {
    super(driver);
  }

  @FindBy(how = How.XPATH, using = "//input[@name='data']")
  public WebElement todoText;

  @FindBy(how = How.XPATH, using = "//select[@name='category']")
  public WebElement selectCategory;

  @FindBy(how = How.XPATH, using = "//select[@name='due_day']")
  public WebElement SelectDueDay;

  @FindBy(how = How.XPATH, using = "//select[@name='due_month']")
  public WebElement SelectDueMonth;

  @FindBy(how = How.XPATH, using = "//select[@name='due_year']")
  public WebElement SelectDueYear;

  @FindBy(how = How.XPATH, using = "//input[@value='Add']")
  public WebElement addTodoButton;

  @FindBy(how = How.XPATH, using = "//input[@name='categorydata']")
  public WebElement addCategoryTextField;

  @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Advanced")
  public WebElement advancedLink;

  @FindBy(how = How.XPATH, using = "//select[@name='colour']")
  public WebElement SelectCategoryColor;

  @FindBy(how = How.XPATH, using = "//input[@value='Add category']")
  public WebElement addCategoryColorButton;

  @FindBy(how = How.XPATH, using = "//input[@value='Remove']")
  public WebElement removeButton;

  @FindBy(how = How.XPATH, using = "//input[@value='Complete']")
  public WebElement completeButton;
}
