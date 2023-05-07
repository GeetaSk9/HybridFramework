package com.qa.opencart.tests;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SearchResultTestPage extends BaseTest{
	
	
	@BeforeClass
	public void searchReslPageSetUp() throws InterruptedException {
		searchReslPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
//	@DataProvider
//	public Object[][] productData() {
//		return new Object[][] {
//			{"oneplus","OnePlus 10R 5G Prime Edition (Prime Blue, 8GB RAM, 128GB Storage, 80W SuperVOOC)"},
//			{"Apple","Apple iPhone 14 (128 GB) - Blue"},
//			{"Samsung","Samsung Galaxy M14 5G (ICY Silver, 4GB, 128GB Storage) | 50MP Triple Cam | 6000 mAh Battery | 5nm Octa-Core Processor | Android 13 | Without Charger"},
//		};
//	}
	
//	@Test(dataProvider = "productData")
//	public void searchResultTest(String prodcutname) {
//		searchReslPage = loginPage.doSearch(prodcutname);
//		Assert.assertTrue(searchReslPage.getProductListCount()>0);
//	}
	
//	@Test(dataProvider = "productData")
//	public void selectProductTest(String productName, String mainProductName) {
//		searchReslPage = loginPage.doSearch(productName);
//		productInfoPage = searchReslPage.selectProduct(mainProductName);
//		
//		//Get handles of the windows
//        String mainWindowHandle = driver.getWindowHandle();
//        Set<String> allWindowHandles = driver.getWindowHandles();
//        Iterator<String> iterator = allWindowHandles.iterator();
//
//        // Here we will check if child window has other child windows and will fetch the heading of the child window
//        while (iterator.hasNext()) {
//            String ChildWindow = iterator.next();
//                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
//                driver.switchTo().window(ChildWindow);
////                WebElement text = driver.findElement(By.id("sampleHeading"));
////                System.out.println("Heading of child window is " + text.getText());
//                
//                String prodnameActual = productInfoPage.getTextOfProdcutname();
//                Assert.assertEquals(prodnameActual, mainProductName);
//                break;
//            }
//                
//        }
//        driver.close();
//        driver.switchTo().window(mainWindowHandle);
//		
//		
//		
//	}
	
	@Test
	public void selectProductTest() {
		searchReslPage = loginPage.doSearch("oneplus");
		productInfoPage = searchReslPage.selectProduct("Redmi Note 12 5G Matte Black 6GB RAM 128GB ROM | 1st Phone with 120Hz Super AMOLED and Snapdragon® 4 Gen 1 | 48MP AI Triple Camera");
		
		//Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
//                WebElement text = driver.findElement(By.id("sampleHeading"));
//                System.out.println("Heading of child window is " + text.getText());
                
                String prodnameActual = productInfoPage.getTextOfProdcutname();
                Assert.assertEquals(prodnameActual, "Redmi Note 12 5G Matte Black 6GB RAM 128GB ROM | 1st Phone with 120Hz Super AMOLED and Snapdragon® 4 Gen 1 | 48MP AI Triple Camera");
                
                Map<String,String> actualProdctInfoMap = productInfoPage.getProductInfo();
                actualProdctInfoMap.forEach((k,v)->System.out.println(k+ ": " +v));
                softAssert.assertEquals(actualProdctInfoMap.get("name"), "Redmi Note 12 5G Matte Black 6GB RAM 128GB ROM | 1st Phone with 120Hz Super AMOLED and Snapdragon® 4 Gen 1 | 48MP AI Triple Camera");
                softAssert.assertEquals(actualProdctInfoMap.get("Brand"),"Redmi");
                softAssert.assertEquals(actualProdctInfoMap.get("Model Name"),"Redmi Note 12 5G");
                softAssert.assertAll();
                break;
            }
                
        }
        driver.close();
        driver.switchTo().window(mainWindowHandle);
		
		
		
	}
	

}
