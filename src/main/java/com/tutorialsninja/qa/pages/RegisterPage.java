package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBox;

	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBox;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;

	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmTextBox;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement subscribeYesRadioButton;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='2']")
	private WebElement subscribeNoRadioButton;
	
	@FindBy(name = "agree")
	private WebElement agreeCheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement warningEmailAlreadyRegistered;
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void enterFirstName(String firstName) {
		firstNameTextBox.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameTextBox.sendKeys(lastName);
	}
	
	public void enterEmailAddress(String email) {
		emailTextBox.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		telephoneTextBox.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		passwordTextBox.sendKeys(password);
	}
	
	public void enterConfirmPassword(String password) {
		confirmTextBox.sendKeys(password);
	}
	
	public void selectYesRadioButton() {
		subscribeYesRadioButton.click();
	}
	
	public void clickOnAgreeCheckBox() {
		agreeCheckBox.click();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName, String lastName, String email, String telephone, String password) throws InterruptedException {
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.sendKeys(email);
		telephoneTextBox.sendKeys(telephone);
		passwordTextBox.sendKeys(password);
		confirmTextBox.sendKeys(password);
		agreeCheckBox.click();
		continueButton.click();
		Thread.sleep(2000);
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName, String lastName, String email, String telephone, String password) throws InterruptedException {
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.sendKeys(email);
		telephoneTextBox.sendKeys(telephone);
		passwordTextBox.sendKeys(password);
		confirmTextBox.sendKeys(password);
		subscribeYesRadioButton.click();
		agreeCheckBox.click();
		continueButton.click();
		Thread.sleep(2000);
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithExistingEmailAddress(String firstName, String lastName, String email, String telephone, String password) throws InterruptedException {
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.sendKeys(email);
		telephoneTextBox.sendKeys(telephone);
		passwordTextBox.sendKeys(password);
		confirmTextBox.sendKeys(password);
		subscribeYesRadioButton.click();
		agreeCheckBox.click();
		continueButton.click();
		Thread.sleep(2000);
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveWarningEmailAlreadyRegistered() {
		return warningEmailAlreadyRegistered.getText();
	}


}
