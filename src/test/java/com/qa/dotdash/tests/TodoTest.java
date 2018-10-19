package com.qa.dotdash.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.dotdash.pageobjects.TodoPO;
import com.qa.dotdash.pageobjects.DuplicteTodoPO;

public class TodoTest extends BaseUITest {

  @Test
  public void addBasicTodo() {
    TodoPO category = new TodoPO(driver);
    category.advancedLink.click();
    category.todoText.sendKeys("spinach");
    category.addTodoButton.click();
  }

  @Test
  public void addDuplicateBasicTodo() {
    TodoPO categoryPO = new TodoPO(driver);
    // remove existing elements with spinach
    removeTodo("Spinach");

    // create item
    categoryPO.todoText.sendKeys("Spinach");
    categoryPO.addTodoButton.click();
    // create duplicate todo
    categoryPO.todoText.sendKeys("Spinach");
    categoryPO.addTodoButton.click();

    // check for error message/links
    DuplicteTodoPO errorPage = new DuplicteTodoPO(driver);
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

    @Test
  public void addTodoWithAdvancedOptions() {
    TodoPO category = new TodoPO(driver);
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
    // Add one to month {0 - 11}
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

}
