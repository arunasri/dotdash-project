package com.qa.dotdash.tests;

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
  public void SetUP() {
    driver = new ChromeDriver();
    driver.get(URL);
  }

  @Test
  public void addingCategoryTestcase() {
    CategoryPO category = new CategoryPO(driver);
    // remove existing category if exists
    removeCategory("Bills");
    // Category Textfield
    category.CategoryTextField.sendKeys("Bills");
    // Selecting color for category
    Select dropdown1 = new Select(category.SelectCategory);
    dropdown1.selectByVisibleText("Yellow");
    // Clicking on Add Color Category Button
    category.addCategoryColorButton.click();
    // Remove Button
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
    category.CategoryTextField.sendKeys("spinach");
    category.addCategoryAddButton.click();
  }

  @Test
  public void addDuplicateBasicTodo() {
    CategoryPO categoryPO = new CategoryPO(driver);
    // remove existing elements with spinach
    removeTodo("Spinach");

    // create item
    categoryPO.CategoryTextField.sendKeys("Spinach");
    categoryPO.addCategoryAddButton.click();
    // create duplicate todo
    categoryPO.CategoryTextField.sendKeys("Spinach");
    categoryPO.addCategoryAddButton.click();

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
  public void addTodoWithCategory() {
    CategoryPO category = new CategoryPO(driver);
    // Category Textfield
    category.CategoryTextField.sendKeys("Bills");
    // selecting category
    Select dropdown1 = new Select(category.SelectCategory);
    dropdown1.selectByVisibleText("Personal");
    // Selecting Due Day
    Select dropdown2 = new Select(category.SelectDueDay);
    dropdown2.selectByValue("19");
    // Selecting Due Month
    Select dropdown3 = new Select(category.SelectDueMonth);
    dropdown3.selectByValue("10");
    // Selecting Due Year
    Select dropdown4 = new Select(category.SelectDueYear);
    dropdown4.selectByValue("2018");
    // Clicking on Category button
    category.addCategoryAddButton.click();
    // Selecting color for category
    // category.SelectCategoryColor.selectByVisibleText("Yellow");
    // Add category name
    category.addCategoryTextField.sendKeys("Bills");
    // Clicking on Add Color Category Button
    category.addCategoryColorButton.click();
    // Remove Button
    category.removeButton.click();
    category.completeButton.click();
  }

  @AfterMethod
  public void TearDown() {
    driver.close();
  }
}
