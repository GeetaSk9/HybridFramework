package com.qa.opencart.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constans;

public class LoginTest extends BaseTest{
	
	@Test(priority = 2)
	public void doLoginTest() throws InterruptedException {
		
		loginPage.doLogin(prop.getProperty("userName"),prop.getProperty("password"));
	}
	
	@Test(priority = 3)
	public void loginPageTitleTest() {
		
		String actTitle  = loginPage.getLoginPageTitle();
		System.out.println("Page title is " +actTitle);
		//Assert.assertEquals(actTitle, actTitle);
	}
}
