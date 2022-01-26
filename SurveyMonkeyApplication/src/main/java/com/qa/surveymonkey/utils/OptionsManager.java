package com.qa.surveymonkey.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	//Step 1
	public Properties prop;
	public ChromeOptions co;
	public FirefoxOptions fo;
	
	//Step 2 - Create Constructor
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	//Step 3 (co is ChromeOptions so we are returning co)
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("cognito"))) co.addArguments("--incognito");
		
		return co;
	}
	
	//Step 4 (fo is FirefoxOptions so we are returning fo)
		public FirefoxOptions getFirefoxOptions() {
			fo = new FirefoxOptions();
			if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
			return fo;
		}
	

}
