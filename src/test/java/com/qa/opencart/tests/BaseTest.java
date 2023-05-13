package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.driverFactory.DriverFactory;
import com.qa.opencart.pages.CreateNewAccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	Properties prop;
	DriverFactory df;
	LoginPage loginPage;
	SearchResultPage searchReslPage;
	ProductInfoPage productInfoPage;
	SoftAssert softAssert;
	CreateNewAccountPage accountPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String brwoserName) {
		df=new DriverFactory();
		prop = df.init_prop();
		if(brwoserName!=null) {
			prop.setProperty("browser", brwoserName);
		}
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
