package com.automationexercise.testCase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationexercise.pageObject.AddProductsInCart;

public class AddProductsInCartTestCase extends BaseClass {

	@Test
	public void AddProductsInCartTestCase_01() throws InterruptedException, IOException {
		logger.info("URL is opened : " + baseURL);
		AddProductsInCart apc = new AddProductsInCart(driver);
		apc.clickOnProductsMenuBtn();
		logger.info("user clicked on product menu");
		String currentUrl = driver.getCurrentUrl();
		logger.info(currentUrl);
		if (currentUrl.equals("https://automationexercise.com/products")) {
			Assert.assertTrue(true);
			logger.info("user verify that products page is visible successfully");
		} else {
			captureScreen(driver, "AddProductsInCartTestCase_01");
			Assert.assertTrue(false);
			logger.info("user verify that products page is not visible successfully");
		}
		scrollDown8000Pixels();
		List<WebElement> ProductDetails = apc.getProductsDetails();
		logger.info("Available ProductDetails: " + ProductDetails.size());
		for (WebElement Product_Details : ProductDetails) {
			logger.info(Product_Details.getText());
		}
	}

	@Test
	public void AddProductsInCartTestCase_02() throws InterruptedException, IOException {
		logger.info("URL is opened : " + baseURL);
		AddProductsInCart apc = new AddProductsInCart(driver);
		apc.clickOnProductsMenuBtn();
		logger.info("user clicked on product menu");
		String currentUrl = driver.getCurrentUrl();
		logger.info(currentUrl);
		if (currentUrl.equals("https://automationexercise.com/products")) {
			Assert.assertTrue(true);
			logger.info("user verify that products page is visible successfully");
		} else {
			captureScreen(driver, "AddProductsInCartTestCase_01");
			Assert.assertTrue(false);
			logger.info("user verify that products page is not visible successfully");
		}
		scrollDown8400Pixels();
		actions.moveToElement(apc.firstProductImageWrapper).build().perform();
		apc.clickFirstProductAddCart();
		logger.info("user Hover over first product and click 'Add to cart'");
		Thread.sleep(3000);
		String ProductAddedInCartMessage = apc.getProductAddedInCartMsg();
		if (ProductAddedInCartMessage.equals("Your product has been added to cart.")) {
			Assert.assertTrue(true);
			logger.info("user verify that product has been added to cart successfully");
		} else {
			captureScreen(driver, "AddProductsInCartTestCase_01");
			Assert.assertTrue(false);
			logger.info("user verify that product has not been added to cart");
		}
		apc.clickOnContinueShoppingBtn();
		logger.info("user click On Continue Shopping ");
		driver.navigate().refresh();
		scrollDown8400Pixels();

		actions.moveToElement(apc.SecondProductImageWrapper).build().perform();
		apc.clickSecondProductAddCart();
		logger.info("user Hover over Second product and click 'Add to cart'");
		Thread.sleep(3000);
		String ProductAddedInCartMessage2 = apc.getProductAddedInCartMsg();
		if (ProductAddedInCartMessage2.equals("Your product has been added to cart.")) {
			Assert.assertTrue(true);
			logger.info("user verify that product has been added to cart successfully");
		} else {
			captureScreen(driver, "AddProductsInCartTestCase_01");
			Assert.assertTrue(false);
			logger.info("user verify that product has not been added to cart");
		}
		apc.clickOnViewCartBtn();
		logger.info("user view the cart details");

		List<WebElement> rowNum = driver.findElements(By.xpath("//table[@class='table table-condensed']/tbody/tr"));
		logger.info("Total rows are : " + rowNum.size());
		List<WebElement> colNum = driver.findElements(By.xpath("//table[@class='table table-condensed']/tbody/tr[1]/td"));
		logger.info("Total cols are : " + colNum.size());
		for (int rows = 1; rows <= rowNum.size(); rows++) {
			for (int cols = 1; cols <= colNum.size(); cols++) {
				logger.info(driver.findElement(By.xpath("//table[@class='table table-condensed']/tbody/tr[" + rows + "]/td[" + cols + "]")).getText() + "   ");
			}
			System.out.println();
		}
	}
}