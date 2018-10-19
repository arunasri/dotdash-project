package com.qa.dotdash.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.dotdash.pageobjects.CategoryPO;
import com.qa.dotdash.pageobjects.DuplictePO;

public class DotDashTest {
  private WebDriver driver;
  private String URL = "http://192.168.64.2/dotdash/index.php";

  @BeforeMethod
  public void SetUp() {
    driver = new ChromeDriver();
    driver.get(URL);
  }

  @Test
  public void addingCategoryTestcase() {
    CategoryPO category = new CategoryPO(driver);
    // remove existing category if exists
    removeCategory("Bills");
    // Category Textfield
    category.addCategoryTextField.sendKeys("Bills");
    // Selecting color for category
    Select colourDropdown = new Select(category.SelectCategoryColor);
    colourDropdown.selectByValue("#0000FF");
    // Clicking on Add Color Category Button
    category.addCategoryColorButton.click();
    
    try {
      WebElement categoryElement = driver.findElement(By.xpath("//span[contains(text(),'Bills')]"));
      // Assert.assertEquals(categoryElement.getAttribute("style"), "color: #0000FF; padding:
      // 2px;");
    } catch (org.openqa.selenium.NoSuchElementException e) {
      Assert.fail("category element not created");
    }
    // remove created category if exists
    removeCategory("Bills");
  }

  @Test
  public void addBasicTodo() {
    CategoryPO category = new CategoryPO(driver);
    category.advancedLink.click();
    category.todoText.sendKeys("spinach");
    category.addTodoButton.click();
  }

  @Test
  public void addDuplicateBasicTodo() {
    CategoryPO categoryPO = new CategoryPO(driver);
    // remove existing elements with spinach
    removeTodo("Spinach");

    // create item
    categoryPO.todoText.sendKeys("Spinach");
    categoryPO.addTodoButton.click();
    // create duplicate todo
    categoryPO.todoText.sendKeys("Spinach");
    categoryPO.addTodoButton.click();

    // check for error message/links
    DuplictePO errorPage = new DuplictePO(driver);
    SoftAssert softAsserts = new SoftAssert();

    softAsserts.assertTrue(
        errorPage.errorMessage.isDisplayed(), "Error message is not present on the page");
    softAsserts.assertTrue(
        errorPage.backLink.isDisplayed(), "Error message is not present on the page");
    errorPage.backLink.click();

    softAsserts.assertEquals(
        "http://192.168.64.2/dotdash/index.php",
        errorPage.driver.getCurrentUrl(),
        "Error message is not present on the page");
    softAsserts.assertAll();
    // remove existing elements with spinach
    removeTodo("Spinach");
  }

  public void removeTodo(String todoName) {
    try {
      CategoryPO categoryPO = new CategoryPO(driver);
      // remove existing elements with spinach
      WebElement existingElement =
          driver.findElement(By.xpath("//li[contains(text(),'" + todoName + "')]"));
      if (existingElement != null) {
        existingElement.findElement(By.cssSelector("input")).click();
        categoryPO.removeButton.click();
      }
    } catch (org.openqa.selenium.NoSuchElementException e) {
    }
  }

  public void removeCategory(String categoryName) {
    try {
      WebElement categoryElement =
          driver.findElement(By.xpath("//span[contains(text(),'" + categoryName + "')]"));
      categoryElement.click();
      driver.findElement(By.partialLinkText("Yes")).click();
    } catch (org.openqa.selenium.NoSuchElementException e) {
    }
  }

  public void deleteBasicTodo() {
    CategoryPO category = new CategoryPO(driver);
  }

  public void completeBasicTodo() {}

  @Test
  public void addTodoWithAdvancedOptions() {
    CategoryPO category = new CategoryPO(driver);
    removeTodo("groceries");
    
    // Category Textfield
    category.todoText.sendKeys("groceries");

    // selecting category
    Select dropdown1 = new Select(category.selectCategory);
    dropdown1.selectByVisibleText("Personal");

    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    //Add one to month {0 - 11}
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    // Selecting Due Day
    Select daySelectBox = new Select(category.SelectDueDay);
    daySelectBox.selectByValue("" + day);
    // Selecting Due Month
    Select monthSelectBox = new Select(category.SelectDueMonth);
    monthSelectBox.selectByValue("" + month);
    // Selecting Due Year
    Select yearSelectBox = new Select(category.SelectDueYear);
    yearSelectBox.selectByValue("" + year);

    // Clicking on Add button
    category.addTodoButton.click();
    // Selecting color for category
    removeTodo("groceries");
  }

  @AfterMethod
  public void TearDown() {
    driver.quit();
  }
}
