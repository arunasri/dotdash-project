package com.qa.dotdash.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.dotdash.pageobjects.TodoPO;

public class BaseUITest extends BaseTest {
  public WebDriver driver;
  private String URL = "/dotdash/index.php";

  @BeforeMethod
  public void SetUp() {
    driver = new ChromeDriver();
    driver.get(baseURL + URL);
  }

  public void removeTodo(String todoName) {
    try {
      TodoPO categoryPO = new TodoPO(driver);
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
    TodoPO category = new TodoPO(driver);
  }

  @AfterMethod
  public void TearDown() {
    driver.quit();
  }
}
