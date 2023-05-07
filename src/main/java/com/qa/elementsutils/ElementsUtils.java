package com.qa.elementsutils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsUtils {

	private WebDriver driver;
	
	public ElementsUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public By getBy(String locatorType, String locatorvalue) {
		
		By locator = null;
		switch(locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorvalue);
			break;
		case "name":
			locator = By.name(locatorvalue);
			break;
		case "classname":
			locator = By.className(locatorvalue);
			break;
		case "xpath":
			locator = By.xpath(locatorvalue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorvalue);
			break;
		case "linktext":
			locator = By.linkText(locatorvalue);
			break;
			
		default:
			System.out.println("Please pass the rite loactor and value...");
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}
	
	public void doClear(By locator) {

		getElement(locator).clear();
	}

	public void doClick(By locator) {

		getElement(locator).click();
	}
	
	public void waitForElementToBeClickable(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void waitForElementToBeVisible(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
