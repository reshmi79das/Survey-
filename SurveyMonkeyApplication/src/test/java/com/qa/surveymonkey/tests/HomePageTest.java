package com.qa.surveymonkey.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.surveymonkey.base.BaseTest;
import com.qa.surveymonkey.pages.HomePage;
import com.qa.surveymonkey.utils.Constants;

public class HomePageTest extends BaseTest {

	HomePage homePage;
	@BeforeClass
	public void homeSetup() {
		homePage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
		
	//6
	@Test(priority = 1)
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is : " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home Page title did not matched....");
	}
		
	//7
	@Test(priority = 2)
	public void verifyHomePageHeader() {
		String dashboardLink = homePage.getDashboardLinkText();
		System.out.println("Dashboard Link text is : " + dashboardLink);
		Assert.assertEquals(dashboardLink, Constants.DASHBOARD_LINK, "Dashboard link is displayed....");
	}
		
	//8
	@Test(priority = 3)
	public void verifyThatUserLoggedIn() {
		String loggedInUser = homePage.getLoggedInUser();
		System.out.println("Logged in user is : " + loggedInUser);
		Assert.assertEquals(loggedInUser, prop.getProperty("username"), "Logged in user did not match...");
	}
		
	@Test(priority = 4) 
	public void mySurveysLinkText() { 
		String mySurveysLink = homePage.getMySurveysLinkText(); 
		System.out.println("My Surveys Link text is : " + mySurveysLink); 
		Assert.assertEquals(mySurveysLink, Constants.MYSURVEYS_LINK, "My Surveys link is displayed...."); 
	}
		
}
