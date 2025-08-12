package me.clockify.app.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input[type='email']")
	WebElement emailInput;
	@FindBy(xpath = "//span[text()='Next']/parent::button")
	WebElement nextButton;
	@FindBy(css = "input[type='password']")
	WebElement passwordInput;
	
	public void enterEmailId(String email) {
		emailInput.sendKeys(email);
	}
	public void enterPassword(String password) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		passwordInput.sendKeys(password);
	}
	public void clickNext() {
		nextButton.click();
	}
}
