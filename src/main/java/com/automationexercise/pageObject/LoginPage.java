package com.automationexercise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "username")

	WebElement txtUserName;

	@FindBy(name = "password")

	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Log In']")

	WebElement btnLogin;

	@FindBy(xpath = "//p[@class='smallText']")

	WebElement UsresNameOnDeskBoard;

	@FindBy(xpath = "//a[contains(text(), 'Log Out')]")

	WebElement logoutLink;

	@FindBy(xpath = "//p[contains(text(), 'An internal error has occurred and has been logged.')]")

	WebElement InvalidCredentialErrorMsg;

	@FindBy(xpath = "//p[contains(text(), 'Please enter a username and password.')]")

	WebElement errorMessageForCredential;

	public void setUsername(String uname) {
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String userPassword) {
		txtPassword.sendKeys(userPassword);
	}

	public String getErrorMessage() {
		return InvalidCredentialErrorMsg.getText();
	}

	public void clickSubmit() {
		btnLogin.click();
	}

	public String getUserNameDeskBoard() {
		return UsresNameOnDeskBoard.getText();
	}

	public void clickLogOut() {
		logoutLink.click();
	}

	public String getErrorMessageForEnterIdAndPassword() {
		return errorMessageForCredential.getText();
	}

}
