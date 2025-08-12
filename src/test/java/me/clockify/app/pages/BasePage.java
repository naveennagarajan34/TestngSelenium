package me.clockify.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		// Automatically initializes and avoids manually initializing each element.
		PageFactory.initElements(driver, this);
	}
}
