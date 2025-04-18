package toolsqa;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WindowHandling {
	WebDriver driver;

	@BeforeClass
	public void setupBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking"); // or try "--disable-notifications"
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}

	@Test
	public void tabHandle() {
		JavascriptExecutor js = (JavascriptExecutor) driver; // to remove known ads in the webpage
		js.executeScript(
				"let ads = document.querySelectorAll('iframe, .adsbygoogle, .google-auto-placed'); ads.forEach(a => a.remove());");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='tabButton']")));
		newTab.click();

		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
			System.out.println("__" + driver.getTitle() + "__");
		}
		driver.switchTo().defaultContent();
	}

	@Test
	public void newWindowHandle() {
		JavascriptExecutor js = (JavascriptExecutor) driver; // to remove known ads in the webpage
		js.executeScript(
				"let ads = document.querySelectorAll('iframe, .adsbygoogle, .google-auto-placed'); ads.forEach(a => a.remove());");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newWindow = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='windowButton']")));
		newWindow.click();

		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
			System.out.println(driver.getTitle());
		}
		driver.switchTo().window(parent);
		driver.quit();
	}
	@Test
	public void navigateGet() {
		driver.get("https://demoqa.com/browser-windows");
		driver.navigate().to("https://www.youtube.com");
	}
}
