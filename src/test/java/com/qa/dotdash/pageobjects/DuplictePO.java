package com.qa.dotdash.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DuplictePO {
    
    @FindBy(how = How.XPATH, using = "//*[text()='Sorry that TODO item already exists. ']")
    public WebElement errorMessage;
    @FindBy(how = How.XPATH, using = "//a[text()='Back']")
    public WebElement backLink;
    // Constructor
    public WebDriver driver;
    public DuplictePO(WebDriver driver) {
	//Initializing elements
	PageFactory.initElements(driver, this);
	this.driver = driver;
    }
}
