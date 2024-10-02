package com.automationexercise.testCase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.automationexercise.pageObject.LogoutPage;

public class LogoutTestCase extends BaseClass{
	@Test
	public void logoutTest() throws InterruptedException, IOException {

		logger.info("URL is opened");

		LogoutPage lp = new LogoutPage(driver);

		lp.clickSignUpBtn();
		lp.enterEmail(readconfig.getEmailAddress());
		logger.info("User entered the email:");
		lp.enterPassword(readconfig.getPassword());
		logger.info("User entered the password:");
		lp.clickOnLoginBtn();
		logger.info("User clicked on Login Button");

	}



}
