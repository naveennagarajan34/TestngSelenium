package toolsqa;

import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FeaturesOfSelenium {

	WebDriver driver;

	@BeforeClass
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/browser-windows");
		driver.manage().window().maximize();
	}

	@Test
	public void windowSize() {
		driver.manage().window().setSize(new Dimension(1024, 706));
		Dimension size = driver.manage().window().getSize();
		System.out.println(size); // dimensions of the screen
		System.out.println("Width: " + size.getWidth());
		System.out.println("Height: " + size.getHeight());

		Point position = driver.manage().window().getPosition();
		System.out.println("X position in px: " + position.getX()); // pixels from the left
		System.out.println("Y position in px: " + position.getY()); // pixels from the top

		driver.manage().window().setPosition(new Point(1000, 1000));// sets the position of the window
	}

	@Test
	public void screenshotMethod() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Create a timestamp to make the new name unique
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File saveAs = new File("screenshots/screenshot_" + timestamp + ".png");
		try {
			FileUtils.copyFile(screenshot, saveAs);
		} catch (IOException error) {
			System.err.println("Error occurred while saving the screenshot: " + error.getMessage());
			error.printStackTrace();
		}
	}

	@AfterClass
	public void tearDownBrowser() throws InterruptedException {
//		Thread.sleep(5000);
		driver.quit();
	}
}
