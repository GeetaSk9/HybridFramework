package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Text;

public class SearchResultPage {
	
	private WebDriver driver;
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h2//a") 
	private List<WebElement> listOfResults;
	
	public int getProductListCount() {
		int resultCount = listOfResults.size();
		System.out.println("prodct result counts is " +resultCount);
		return resultCount;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName) {
		
		System.out.println("Main product name is " +mainProductName);
		
		List<WebElement> searchList = listOfResults;
		for(WebElement e: searchList) {
			String text = e.getText();
			if(text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
	}
	
		
}
