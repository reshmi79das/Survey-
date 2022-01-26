package com.qa.surveymonkey.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil{
	//1
	WebDriver driver;
	JavaScriptUtil jsUtil; //Calling JavaScriptUtil
	
	//2 - Constructor for ElementUtil
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver); //Calling JavaScriptUtil
	}
	
	//3
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			System.out.println("Locator is : " + locator);
			element = driver.findElement(locator);
			jsUtil.flash(element); //Calling JavaScriptUtil
			jsUtil.drawBorder(element); //Calling JavaScriptUtil
			System.out.println("Webelement is created successfully : " + locator);
		}catch(Exception e) {
			System.out.println("Some exception got occured with this locator : " + locator);
		}
		return element;
	}
	
	//4 - To enter the values in any input field
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value); 
	}
	
	//5 - - To click on any element (Button, Link, Checkbox, Image, Radiobutton)
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	//6
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	//7
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	//8 - Explicit Wait to wait untill title is present
	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	//9 - Waiting for an element to be visible
	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
}
