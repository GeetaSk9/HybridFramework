package com.qa.opencart.tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.elementsutils.ExcelUtil;

public class CreateNewAccountTest extends BaseTest {

	@BeforeClass
	public void setUpCreateNewAccount() throws InterruptedException {
		accountPage = loginPage.createNewAccount();
	}
	
	public String getRandomEmail() {
		Random randomEmail = new Random();
		String email = "autmation"+randomEmail.nextInt(1000)+"@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getCreateAccountData() {
		return ExcelUtil.getTestData("NewAccount");
	}

	@Test(dataProvider = "getCreateAccountData")
	public void createNewAccountTest(String name, String pwd, String ph) throws InterruptedException {
		accountPage.createNewAcct(name, pwd, ph, getRandomEmail());
	}

}
