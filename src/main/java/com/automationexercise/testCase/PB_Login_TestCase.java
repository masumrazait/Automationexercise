package com.automationexercise.testCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.pageObject.LoginPage;

public class PB_Login_TestCase extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {

		logger.info("URL is opened");

		LoginPage lp = new LoginPage(driver);

		lp.setUsername(readconfig.getUsernameID());
		logger.info("User entered the User Name:");

		lp.setPassword(readconfig.getUserPasswordId());
		logger.info("User entered the password:");
		Thread.sleep(2000);
		lp.clickSubmit();
		logger.info("User clicked on Login Button");
		Thread.sleep(2000);

		if (driver.getTitle().equals("ParaBank | Accounts Overview")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}

		lp.clickLogOut();

		if (driver.getTitle().equals("ParaBank | Welcome | Online Banking")) {
			Assert.assertTrue(true);
			logger.info("User successfully logout!");
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("User not able to logout!");
		}

	}

	@Test(priority = 1)
	public void loginTestInvalidCredentials() throws IOException, InterruptedException {
		logger.info("URL is opened");

		LoginPage lp = new LoginPage(driver);
		lp.setUsername(readconfig.getInvalidUser());
		logger.info("Entered Invalid User Id");
		Thread.sleep(3000);
		lp.setPassword(readconfig.getWrongPassword());
		logger.info("Entered Wrong password");

		lp.clickSubmit();
		Thread.sleep(3000);

		if (lp.getErrorMessage().equals("An internal error has occurred and has been logged.")) {
			Assert.assertTrue(false);
			logger.info("User get error Msg An internal error has occurred and has been logged.");
		} else {
			captureScreen(driver, "loginTestInvalidCredentials");
			Assert.assertTrue(true);
			logger.info("User donot get any error msg An internal error has occurred and has been logged.");
		}
	}

	@Test(priority = 2)
	public void loginTestWithoutCredentials() throws IOException, InterruptedException {
		logger.info("URL is opened");

		LoginPage lp = new LoginPage(driver);
		logger.info("User did not entered userId");
		logger.info("User did not entered password");

		// lp.clickSubmit();
		logger.info("User click on login button.");

		Thread.sleep(5000);
		/*
		 * WebDriverWait wait=new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.titleContains(lp.
		 * getErrorMessageForEnterIdAndPassword()));
		 * 
		 * if (lp.getErrorMessage().equals("Please enter a username and password.")) {
		 * Assert.assertTrue(true); logger.info("User get error Msg!"); } else {
		 * captureScreen(driver, "loginTestWithoutCredentials");
		 * Assert.assertTrue(false); logger.info("User donot get any error msg!"); }
		 */
	}

}
