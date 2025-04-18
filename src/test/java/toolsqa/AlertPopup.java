package toolsqa;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertPopup {
	WebDriver driver;

	@BeforeClass
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/alerts");
	}

	@Test
	public void alertAccept() throws InterruptedException {
		Thread.sleep(3000);
		WebElement alertButton = driver.findElement(By.xpath("//button[@id='alertButton']"));
		alertButton.click();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}
	
	@Test
	public void alertAcceptWithDelay() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alertButton = driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
		alertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}
	
	@Test
	public void alertAcceptConfirmCancel() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alertButton = driver.findElement(By.xpath("//button[@id='confirmButton']"));
		alertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(3000);
		alertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}
	
	@Test
	public void promptBox() throws InterruptedException {
		Thread.sleep(3000);
		WebElement promptButton = driver.findElement(By.xpath("//button[@id='promtButton']"));
		promptButton.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		System.out.println("Alert Text: " + alert.getText());
		alert.sendKeys("Hello");
		alert.accept();
	}
}
