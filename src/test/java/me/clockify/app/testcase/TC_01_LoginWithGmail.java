package me.clockify.app.testcase;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import me.clockify.app.BaseClass;
import me.clockify.app.utilities.LoginUtility;

public class TC_01_LoginWithGmail extends BaseClass {
	@Test
	public void verifyLogin() {
		LoginUtility.performLogin(driver, "naveen.n@ecgroup-intl.com", "naveT23LMN");
		assertTrue(LoginUtility.isLoggedIn(driver));
	}
}
