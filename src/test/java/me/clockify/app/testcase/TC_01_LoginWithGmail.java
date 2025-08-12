package me.clockify.app.testcase;

import java.time.Duration;

import org.testng.annotations.Test;

import me.clockify.app.BaseClass;
import me.clockify.app.pages.HomePage;
import me.clockify.app.pages.LoginPage;

public class TC_01_LoginWithGmail extends BaseClass {
	@Test
	public void verifyLogin() {
		HomePage home = new HomePage(driver);
		home.ClickLoginWithGoogle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailId("naveen.n@ecgroup-intl.com");
		login.clickNext();


		login.enterPassword("naveT23LMN");
		login.clickNext();
	}
}
