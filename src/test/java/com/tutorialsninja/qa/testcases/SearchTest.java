package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public SearchTest() {
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializingBrowserAndOpenApplicationURL(prop1.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct(){
		searchPage = homePage.searchForAProduct(prop2.getProperty("validSearchText"));
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchForAProduct(prop2.getProperty("invalidSearchText"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), "abcd","No product message in search results is displayed");
	}

	@Test(priority = 3, dependsOnMethods= {"verifySearchWithValidProduct()", "verifySearchWithInvalidProduct()"})
	public void verifySearchWithoutAnyProduct() {
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), prop2.getProperty("noProductTextInSearchPage"),"No product message in search results is displayed");
	}

}
