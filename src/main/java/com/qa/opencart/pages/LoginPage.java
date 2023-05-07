package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.elementsutils.ElementsUtils;

public class LoginPage {

	private WebDriver driver;

	ElementsUtils eleUtils;

//	private By userName = By.xpath("//input[@id='ap_email']");
//	private By continueBtn = By.xpath("//input[@id='continue']");
//	private By password = By.xpath("//input[@type='password']");
//	private By signIn = By.xpath("//input[@id='signInSubmit']");
//	private By moveToSignIn = By.xpath("//a[@data-nav-role='signin']");
//	private By clickOnSignIn = By.xpath("//span[text()='Sign in']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// eleUtils = new ElementsUtils(driver);
	}

	public String getLoginPageTitle() {
		return driver.getTitle();

	}

	@FindBy(xpath = "//input[@id='ap_email']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	private WebElement signIn;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement enterProductName;

	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	private WebElement clickOnsearchBtn;

	@FindBy(xpath = "//a[@id='createAccountSubmit']")
	private WebElement createNewAccount;

	public void moveToSignIn() throws InterruptedException {
//		WebElement ele = driver.findElement(By.xpath("//span[text()='Sign in']"));
//		//eleUtils.waitForElementToBeVisible(ele, 20);
//		 Actions actions = new Actions(driver);
//         actions.moveToElement(ele).perform();
//         eleUtils.waitForElementToBeClickable(moveToSignIn, 20);
//		eleUtils.doClick(moveToSignIn);
//		Thread.sleep(5000);

		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.id("nav-link-accountList"));
		actions.moveToElement(mainMenu);
		actions.build().perform();

		WebElement subMenu = driver.findElement(By.cssSelector("span.nav-action-inner"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(5000);

	}

	public void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 2px solid red;');", element);
	}

	public SearchResultPage doLogin(String un, String pwd) throws InterruptedException {

		moveToSignIn();
		highLighterMethod(driver, userName);
		this.userName.click();
		this.userName.sendKeys(un);
		this.continueBtn.click();
		Thread.sleep(5000);
		this.password.click();
		this.password.sendKeys(pwd);
		Thread.sleep(5000);
		this.signIn.click();
		return new SearchResultPage(driver);
	}

	public SearchResultPage doSearch(String productName) {
		System.out.println("searching for a Product");
		enterProductName.clear();
		enterProductName.sendKeys(productName);
		clickOnsearchBtn.click();
		return new SearchResultPage(driver);
	}

	public CreateNewAccountPage createNewAccount() throws InterruptedException {
		moveToSignIn();
		createNewAccount.click();
		return new CreateNewAccountPage(driver);

	}

	
}
