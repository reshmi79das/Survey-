package com.qa.surveymonkey.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.surveymonkey.base.BasePage;
import com.qa.surveymonkey.utils.Constants;
import com.qa.surveymonkey.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;//Calling ElementUtil
	
	//Creating Page Objects
	By dashboard = By.linkText("Dashboard");
	By emailid = By.xpath("//a[@id='userAcctTab_MainMenu']");
	By mySurveys = By.linkText("My Surveys");
	
	//Create a Constructor for HomePage Class
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);//Calling ElementUtil
	}
	
	//Returning Home Page Title
	public String getHomePageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);//Calling ElementUtil
	}
	
	//Returning Dashboard Link
	public String getDashboardLinkText() {
		if (driver.findElement(dashboard).isDisplayed()) {
			//return driver.findElement(dashboard).getText();
			return elementUtil.doGetText(dashboard);//Calling ElementUtil
		}
		return null;
	}

	public String getLoggedInUser() {
		if (driver.findElement(emailid).isDisplayed()) {
			//return driver.findElement(emailid).getText();
			return elementUtil.doGetText(emailid);//Calling ElementUtil
		}
		return null;
	}
		
	public String getMySurveysLinkText() {
		if (driver.findElement(mySurveys).isDisplayed()) {
			//return driver.findElement(mySurveys).getText();
			return elementUtil.doGetText(mySurveys);//Calling ElementUtil
		}
		return null;
	}
	
}
