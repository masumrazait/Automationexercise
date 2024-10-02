package com.automationexercise.pageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductsInCart {
	WebDriver ldriver;

	public AddProductsInCart(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//a[@href='/products']")
	WebElement productsMenuBtn;

	@FindBy(xpath = "(//div[@class='productinfo text-center'])[1]")
	List<WebElement> productsDetails;

	@FindBy(xpath = "(//div[@class='product-image-wrapper'])[1]")
	public WebElement firstProductImageWrapper;

	@FindBy(xpath = "(//a[@data-product-id='1'])[2]")
	WebElement firstProduct;

	@FindBy(xpath = "//p[text()='Your product has been added to cart.']")
	WebElement ProductAddedInCartMsg;

	@FindBy(xpath = "//button[text()='Continue Shopping']")
	WebElement ContinueShoppingBtn;

	@FindBy(xpath = "(//div[@class='product-image-wrapper'])[2]")
	public WebElement SecondProductImageWrapper;

	@FindBy(xpath = "(//div[@class='productinfo text-center'])[2]")
	WebElement productDetailsSecond;

	@FindBy(xpath = "(//a[@data-product-id='2'])[2]")
	WebElement SecondProduct;

	@FindBy(xpath = "(//a[@href='/view_cart'])[2]")
	WebElement view_cartBtn;

	public void clickOnProductsMenuBtn() {
		productsMenuBtn.click();
	}

	public List<WebElement> getProductsDetails() {
		return productsDetails;
	}

	public void clickFirstProductAddCart() {
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", firstProduct);
	}
	
	public void clickSecondProductAddCart() {
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SecondProduct);
	}
	

	public String getProductAddedInCartMsg() {
		return ProductAddedInCartMsg.getText();
	}

	public void clickOnContinueShoppingBtn() {
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", ContinueShoppingBtn);
	}

	public void clickOnViewCartBtn() {
		view_cartBtn.click();
	}

}
