package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	
	private WebDriver driver;
	private Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@id='productTitle']") 
	private WebElement productName;
	
	@FindBy(xpath = "//div[@id='altImages']/descendant::ul/descendant::li[@class='a-spacing-small item imageThumbnail a-declarative']") 
	private List<WebElement> productImagesList;
	
	@FindBy(xpath = "//table[@class='a-normal a-spacing-micro']//tr//td[1]//span") 
	private List<WebElement> productMetaDataListKey;
	
	@FindBy(xpath = "//table[@class='a-normal a-spacing-micro']//tr//td[2]//span") 
	private List<WebElement> productMetaDataListValue;
	
	
	
	public String getTextOfProdcutname() {
		
		String prodname = productName.getText();
		System.out.println("Produsct name on Product info page" +prodname);
		return prodname;
		
	}
	
	public int getProductImageCount() {
		int resultCount = productImagesList.size();
		System.out.println("prodct image counts is " +resultCount);
		return resultCount;
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getTextOfProdcutname());
		getProductMetaData();
		return productInfoMap;
	}
	
	
	private void getProductMetaData() {
		List<WebElement> metadataListKey = productMetaDataListKey;
		List<WebElement> metadataListValue = productMetaDataListValue;
		
		for(int i=0;i<metadataListKey.size();i++) {
			for(int j=0;j<=i;j++) {
//			String text = e.getText();
			
//			String meta[] = text.split(" ");
//			String metaKey = meta[0].trim();
//			String metaValue = meta[1].trim();	
			productInfoMap.put(metadataListKey.get(i).getText(), metadataListValue.get(j).getText());
			}
		}
	}

}
