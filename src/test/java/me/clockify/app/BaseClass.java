package me.clockify.app;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public static WebDriver driver;

	@BeforeClass
	public void setupBrowser() {
		String browser = "chrome";
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			// Disable setup prompts
			options.addArguments("--disable-sync");
			options.addArguments("--no-first-run");
			options.addArguments("--no-default-browser-check");
			options.addArguments("--disable-component-update");
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

	@AfterClass
	public void cleanup() {
//		driver.quit();
	}
}
