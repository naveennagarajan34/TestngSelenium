package me.clockify.app.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import me.clockify.app.pages.HomePage;
import me.clockify.app.pages.LoginPage;

public class LoginUtility {
	public static void performLogin(WebDriver driver, String email, String password) {
		HomePage home = new HomePage(driver);
		home.ClickLoginWithGoogle();

		LoginPage login = new LoginPage(driver);
		login.enterEmailId(email);
		login.enterPassword(password);
		login.clickNext();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public static boolean isLoggedIn(WebDriver driver) {
		LoginPage login = new LoginPage(driver);
		return login.isLoginSuccessful();
	}
}
