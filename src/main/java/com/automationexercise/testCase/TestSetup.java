package com.automationexercise.testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSetup {
	public static void main(String[] args) {
        WebDriverManager.chromedriver().driverVersion("129.0.6668.60").setup();
		WebDriver driver = new ChromeDriver();

		// Your test code here
		driver.get("https://www.GOOGLE.com");

		// Close the browser
		driver.quit();
	}
}
