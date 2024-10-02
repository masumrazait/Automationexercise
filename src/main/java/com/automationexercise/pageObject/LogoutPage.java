package com.automationexercise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
	WebDriver ldriver;

	public LogoutPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),' Signup / Login')]")
	WebElement btnSiginUpMenu;
	
	@FindBy(xpath="//h2[text()='Login to your account']")
	WebElement txtValidationLoginAc;

	@FindBy(xpath="//input[@type='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement clickLoinBtn;
	
	public void clickSignUpBtn() {
		btnSiginUpMenu.click();
		
	}
	
	public void verifyTxtLoginAc() {
		txtValidationLoginAc.getText();
		
	}
		
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);;
		
	}
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);;
		
	}
	public void clickOnLoginBtn() {
		clickLoinBtn.click();
		
	}

	
	


}
