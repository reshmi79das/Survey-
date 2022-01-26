package com.qa.surveymonkey.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.surveymonkey.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//Using below line of code for parallel execution
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 			//ThreadLocal1
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();						  
	}												  
	
	//1
	WebDriver driver;
	
	Properties prop;
	
	public OptionsManager optionsManager; //1

	//2	
	public WebDriver init_driver(Properties prop) {
		optionsManager = new OptionsManager(prop); //2
		String browserName = prop.getProperty("browsername"); 
		
		//3
		if (browserName.equalsIgnoreCase("chrome")) { 
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(); 
			//tlDriver.set(new ChromeDriver()); //For parallel Execution
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); //3
			}
		//4
		else if(browserName.equalsIgnoreCase("firefox")) { 
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			//tlDriver.set(new FirefoxDriver());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		//5
		else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup(); 
			//driver = new InternetExplorerDriver();
			tlDriver.set(new InternetExplorerDriver());
		}
		
		//6
		/*driver.manage().deleteAllCookies(); 		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/ 
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		//7
		//driver.get(prop.getProperty("url"));
		
		getDriver().get(prop.getProperty("url"));
		
		//8
		//return driver;
		
		return getDriver();
	}
	
	/**
	 * This method is used for initialize properties from config.properties
	 * @return
	 */
	//9 :- Creating this method to interact with Properties file
	public Properties init_prop() {
		//11
		prop = new Properties();
		
		String path = null;
		String env = null;
		//Below line of code is used for running the scripts on multiple environments
		try {
			env = System.getProperty("env");
			
			if (env == null) {
				path = "./src/main/java/com/qa/surveymonkey/config/config.properties";
			}else if(env.equals("qa")) {
				path = "./src/main/java/com/qa/surveymonkey/config/qa.config.properties";
			}else if(env.equals("uat")) {
				path = "./src/main/java/com/qa/surveymonkey/config/uat.config.properties";
			}else {
				System.out.println("Please pass the correct path");
			}
			
			//FileInputStream ip = new FileInputStream("./src/main/java/com/qa/surveymonkey/config/config.properties");
			FileInputStream ip = new FileInputStream(path);
			
			//12
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	//Capture Screenshots of the application on failure
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
