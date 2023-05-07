package com.qa.driverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		System.out.println("browser name is " +browserName);
		
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.geFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.geFirefoxOptions()));
		}
		else if(browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}else {
			System.out.println("Please pass the rite browser");
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/*
	 *  Thread local driver creates the copy of Webdriver to run in parallel
	 *  to avoid dead lock
	 */
	
	public static synchronized WebDriver getDriver() {
		
		return tlDriver.get();
	}
	
	
	public Properties init_prop() {
		
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		
		if(envName == null) {
			System.out.println("Running on prod env: " +envName);
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Running on environment: " +envName);
			try {
				switch (envName) {
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
					
				case "tst":
					ip = new FileInputStream("./src/test/resources/config/config.tst.properties");
					break;

				default:
					System.out.println("Please pass the rite environment... ");
					break;
				}
			}catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
	

}
