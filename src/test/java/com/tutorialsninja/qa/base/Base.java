package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop1;
	public Properties prop2;

	public Base() {
		prop1 = new Properties();
		File propFile1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis1 = new FileInputStream(propFile1);
			prop1.load(fis1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		prop2=new Properties();
		File propFile2 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\testData.properties");
		try {
			FileInputStream fis2 = new FileInputStream(propFile2);
			prop2.load(fis2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public WebDriver initializingBrowserAndOpenApplicationURL(String browserName) throws InterruptedException {

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop1.getProperty("url"));
		Thread.sleep(100);

		return driver;
	}

}
