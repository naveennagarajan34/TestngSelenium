package me.clockify.app;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public static WebDriver driver;

	@BeforeSuite
	public void setupBrowser() {
		String browser = "chrome";
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			// Disable setup prompts
			options.addArguments(Arrays.asList("--disable-sync", // Most important - disables sync entirely
					"--disable-default-apps", "--no-first-run",
					"--disable-features=SigninProfileCreation,AccountConsistency", "--disable-signin-scoped-device-id",
					"--disable-web-resources", "--disable-browser-side-navigation", "--disable-prompt-on-repost",
					"--no-default-browser-check", "--disable-component-update"));
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("signin.allowed_on_next_startup", false);
			prefs.put("signin.prohibited", true);
			prefs.put("browser.signin.detection_mode", 0); // Disable sign-in detection
			prefs.put("browser.safebrowsing.enabled", false);
			prefs.put("autofill.profile_enabled", false);
			prefs.put("autofill.credit_card_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			// Hide automation flags
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "enable-logging"));
			options.setExperimentalOption("useAutomationExtension", false);

			driver = new ChromeDriver(options);
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser...");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to("https://app.clockify.me/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterSuite
	public void cleanup() {
//		driver.quit();
	}
}
