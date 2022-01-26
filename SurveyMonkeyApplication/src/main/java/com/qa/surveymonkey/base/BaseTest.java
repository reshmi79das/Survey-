package com.qa.surveymonkey.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.surveymonkey.pages.LoginPage;

public class BaseTest {
	
	//1 :- Class variables as these can be used any where in the class
	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
		
	//2
	@BeforeTest
	public void setup() {
		//3
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		//4
		loginPage = new LoginPage(driver);
	}
		
	//Closing the application
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
