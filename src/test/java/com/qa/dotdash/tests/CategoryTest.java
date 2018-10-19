package com.qa.dotdash.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.dotdash.pageobjects.CategoryPO;

public class CategoryTest extends BaseUITest {
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
  public void removeCategory() {
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
    WebElement categoryElement = driver.findElement(By.xpath("//span[contains(text(),'Bills')]"));
    categoryElement.click();
    driver.findElement(By.partialLinkText("Yes")).click();
    try {
      driver.findElement(By.xpath("//span[contains(text(),'Bills')]"));
      Assert.fail("category element should not be present");
      // Assert.assertEquals(categoryElement.getAttribute("style"), "color: #0000FF; padding:
      // 2px;");
    } catch (org.openqa.selenium.NoSuchElementException e) {
    }
  }
}
