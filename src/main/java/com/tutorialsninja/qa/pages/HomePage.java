package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropDown;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchTextBox;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}

	public LoginPage clickOnLoginOption() {
		loginOption.click();
		return new LoginPage(driver); 
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountDropDown.click();
		loginOption.click();
		return new LoginPage(driver); 
	}

	public RegisterPage clickOnRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropDown.click();
		registerOption.click();
		return new RegisterPage(driver);
	}

	public void enterProductIntoSearchBox(String productText) {
		searchTextBox.sendKeys(productText);
	}

	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchForAProduct(String productText) {
		searchTextBox.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}

}
