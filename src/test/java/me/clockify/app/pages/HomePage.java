package me.clockify.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[contains(text(),'Google')]")
	WebElement loginWithGoogleBtn;

	public void ClickLoginWithGoogle() {
		loginWithGoogleBtn.click();
	}
	
}
