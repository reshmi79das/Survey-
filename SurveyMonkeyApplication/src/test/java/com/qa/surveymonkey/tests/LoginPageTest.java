package com.qa.surveymonkey.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.surveymonkey.base.BaseTest;
import com.qa.surveymonkey.utils.Constants;

//public class LoginPageTest extends BasePage{
public class LoginPageTest extends BaseTest{
	
	//5
	@Test(priority = 2)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is : " + title); //Step 5
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login Page title is not matched");
	}
	
	//6
	@Test(priority = 1) //Step 6
	public void verifySignUpLinkTest() {
		//boolean status = loginPage.verifySignUpLink();
		Assert.assertTrue(loginPage.verifySignUpLink(), "Signup link is not displayed...");
	}
	
	//7
	@Test(priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}
