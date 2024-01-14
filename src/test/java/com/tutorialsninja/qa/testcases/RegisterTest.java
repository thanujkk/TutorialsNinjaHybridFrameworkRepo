package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializingBrowserAndOpenApplicationURL(prop1.getProperty("browserName"));
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() throws InterruptedException {
		accountSuccessPage = registerPage.registerWithMandatoryFields(prop2.getProperty("firstName"),prop2.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),prop2.getProperty("telephone"),prop1.getProperty("validPassword"));
		Assert.assertEquals(prop2.getProperty("createAccountSuccessMessage"), accountSuccessPage.retrieveAccountSuccessPageHeadingText(), "Account Success Page has not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() throws InterruptedException {
		accountSuccessPage = registerPage.registerWithAllFields(prop2.getProperty("firstName"),prop2.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),prop2.getProperty("telephone"),prop1.getProperty("validPassword"));
		Assert.assertEquals(prop2.getProperty("createAccountSuccessMessage"), accountSuccessPage.retrieveAccountSuccessPageHeadingText(), "Account Success Page has not displayed");
	}

	@Test(priority = 3)
	public void verifyRegisteringAccountExistingEmailAddress() throws InterruptedException {
		accountSuccessPage = registerPage.registerWithAllFields(prop2.getProperty("firstName"),prop2.getProperty("lastName"),prop1.getProperty("validEmail"),prop2.getProperty("telephone"),prop1.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveWarningEmailAlreadyRegistered().contains(prop2.getProperty("warningMessageEmailAlreadyRegistered")),"Warning message for E-Mail already registered is not dispalyed");
	}

}
