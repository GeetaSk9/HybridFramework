package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewAccountPage {

	
	private WebDriver driver;

	public CreateNewAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='ap_customer_name']")
	private WebElement enterFisrtAndLastName;
	
	@FindBy(xpath = "//input[@id='ap_phone_number']")
	private WebElement enterPhNumber;
	
	@FindBy(xpath = "//input[@id='ap_email']")
	private WebElement enterOptionalEmail;
	
	@FindBy(xpath = "//input[@id='ap_password']")
	private WebElement enterPassword;
	
	public void createNewAcct(String name,String pwd,String ph,String email) throws InterruptedException {
		enterFisrtAndLastName.click();
		enterFisrtAndLastName.clear();
		enterFisrtAndLastName.sendKeys(name);
		Thread.sleep(2000);
		enterPassword.click();
		enterPassword.clear();
		enterPassword.sendKeys(pwd);
		Thread.sleep(2000);
		enterPhNumber.click();
		enterPhNumber.clear();
		enterPhNumber.sendKeys(ph);
		Thread.sleep(2000);
		enterOptionalEmail.click();
		enterOptionalEmail.clear();
		enterOptionalEmail.sendKeys(email);
		Thread.sleep(2000);
	}
	
	
}
