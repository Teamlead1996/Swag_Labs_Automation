package com.swaglabs.tests;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Add_To_CartTest {
	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com");
	}

	@Test
	public void addToCart() {

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("shopping_cart_container")).click();
		String actualProductName = driver.findElement(By.id("item_4_title_link")).getText();
		System.out.println(actualProductName);
		String expectedProductName = "Sauce Labs Backpack";
		Assert.assertEquals(actualProductName, expectedProductName);
		String actualPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
		String expectedPrice = "$29.99";
		System.out.println(actualPrice);
		Assert.assertEquals(actualPrice, expectedPrice);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();

	}
}
