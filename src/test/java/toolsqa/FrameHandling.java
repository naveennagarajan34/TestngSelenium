package toolsqa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FrameHandling {

	WebDriver driver;

	@BeforeClass
	public void setupBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking"); // or try "--disable-notifications"
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
//		driver.get("https://demoqa.com/frames");
	}

	@Test
	public void frameHandling() throws InterruptedException {
		Thread.sleep(2);
		driver.switchTo().frame("frame1");
		Thread.sleep(2);
		WebElement textFrame_1 = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		System.out.println(textFrame_1.getText());
		driver.switchTo().parentFrame(); // Switches one level up in nested iframes
		// driver.switchTo().defaultContent() Switches all the way out to the main HTML
		// page (top-level)
		Thread.sleep(2);
		driver.switchTo().frame("frame2");
		Thread.sleep(2);
		WebElement textFrame_2 = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		System.out.println(textFrame_2.getText());
	}

	@Test
	public void nestedFrames() throws InterruptedException {
		driver.get("https://demoqa.com/nestedframes");
		// Switch to parent (outer) frame
		driver.switchTo().frame("frame1");
		// Print text in parent frame
		WebElement parentText = driver.findElement(By.tagName("body"));
		System.out.println("Parent frame text: " + parentText.getText());
		// Switch to child frame inside parent
		driver.switchTo().frame(0); // Only one child frame, so index 0 works
		// Print text in child frame
		WebElement childText = driver.findElement(By.tagName("p"));
		System.out.println("Child frame text: " + childText.getText());
		// Switch back to main content
		driver.switchTo().defaultContent();
	}

	@Test
	public void getAllFramesInPage() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> frameList = driver.findElements(By.tagName("iframe"));
		System.out.println("No of frames in the page:" + frameList.size());
		int count = 1;
		for (WebElement frame : frameList) {
			String id = frame.getDomAttribute("id");
			String name = frame.getDomAttribute("name");
			String src = frame.getDomAttribute("src");

			System.out.println("Frame " + count + ": id=" + id + ", name=" + name + ", src=" + src);
			count++;
		}
	}
}
