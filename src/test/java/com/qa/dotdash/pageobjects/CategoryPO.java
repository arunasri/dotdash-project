package com.qa.dotdash.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CategoryPO {
    // Constructor
    public WebDriver driver;
    public CategoryPO(WebDriver driver) {
	//Initializing elements
	PageFactory.initElements(driver, this);
	this.driver = driver;
    }
    
    @FindBy(how = How.XPATH, using = "//input[@name='data']")
    public WebElement CategoryTextField;
    @FindBy(how = How.XPATH, using = "//select[@name='colour']")
    public WebElement SelectCategory;
    @FindBy(how = How.XPATH, using = "//select[@name='due_day']")
    public WebElement SelectDueDay;
    @FindBy(how = How.XPATH, using = "//select[@name='due_month']")
    public WebElement SelectDueMonth;
    @FindBy(how = How.XPATH, using = "//select[@name='due_year']")
    public WebElement SelectDueYear;
    @FindBy(how = How.XPATH, using = "//input[@value='Add']")
    public WebElement addCategoryAddButton;
    @FindBy(how = How.XPATH, using = "//input[@name='categorydata']")
    public WebElement addCategoryTextField;
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Advanced")
    public WebElement advancedLink;
   
    @FindBy(how = How.XPATH, using = "//input[@name='colour']")
    public WebElement SelectCategoryColor;
   
    @FindBy(how = How.XPATH, using = "//input[@value='Add category']")
    public WebElement addCategoryColorButton;
    @FindBy(how = How.XPATH, using = "//input[@value='Remove']")
    public WebElement removeButton;
    @FindBy(how = How.XPATH, using = "//input[@value='Complete']")
    public WebElement completeButton;
   
    
    

}
