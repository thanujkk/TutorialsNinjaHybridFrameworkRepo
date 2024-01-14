package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public LoginTest() {
		super();
	}

	public WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	AccountPage accountPage;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializingBrowserAndOpenApplicationURL(prop1.getProperty("browserName"));
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) {
		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information option is not displayed"); 
	}

	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop2.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(prop2.getProperty("emailPasswordNotMatchingWarningMessage")),"Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop1.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(prop2.getProperty("emailPasswordNotMatchingWarningMessage")),"Expected warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {
		accountPage = loginPage.login(prop1.getProperty("validEmail"), prop2.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(prop2.getProperty("emailPasswordNotMatchingWarningMessage")),"Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(prop2.getProperty("emailPasswordNotMatchingWarningMessage")),"Expected warning message is not displayed");
	}

} 
