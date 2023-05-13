package com.qa.driverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("browser name is " + browserName);

		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {

			/*
			 * run scripts on remote machin
			 */

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {

				init_remoteWebDriver("chrome");

			}

			/*
			 * run scripts on local machin
			 */
			else {
				WebDriverManager.chromedriver().setup();
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equals("firefox")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {

				init_remoteWebDriver("firefox");

			} else {
				WebDriverManager.firefoxdriver().setup();
				// driver = new FirefoxDriver(optionsManager.geFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.geFirefoxOptions()));
			}
		} 
		else if (browserName.equals("edge")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {

				init_remoteWebDriver("edge");

			} else {
				WebDriverManager.edgedriver().setup();
				// driver = new FirefoxDriver(optionsManager.geFirefoxOptions());
				tlDriver.set(new EdgeDriver(optionsManager.geEdgeOptions()));
			}
		}
		
		else if (browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the rite browser");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void init_remoteWebDriver(String browser) {
		
		System.out.println("Running test on grid server : " + browser);
		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "edge":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.geEdgeOptions()));
				break;
			default:
				System.out.println("Pass the rite browser for remote execution..." + browser);
				throw new FrameworkException("NOREMOTEBROSEREXCEPTION");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Thread local driver creates the copy of Webdriver to run in parallel to avoid
	 * dead lock
	 */

	public static synchronized WebDriver getDriver() {

		return tlDriver.get();
	}

	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");

		if (envName == null) {
			System.out.println("Running on prod env: " + envName);
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + envName);
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
			} catch (FileNotFoundException e) {
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

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
