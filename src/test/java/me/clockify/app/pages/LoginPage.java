package me.clockify.app.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		emailInput.sendKeys(email + Keys.ENTER);
	}

	public void enterPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
	}

	public void clickNext() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}

	public boolean isLoginSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#topbar-menu")))
					.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
