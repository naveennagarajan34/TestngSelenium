package toolsqa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Widgets {

	WebDriver driver;

	@BeforeTest
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void accordian() {
		driver.get("https://demoqa.com/accordian");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement accordian3 = driver.findElement(By.id("section3Heading"));
		accordian3.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		accordian3.click();
	}
	
	@Test
	public void datePicker() {
		driver.navigate().to("https://demoqa.com/date-picker");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/date-picker");

        // Click the date input to open the calendar popup
        WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
        dateInput.click();

        // Select the year
        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
        Select selectYear = new Select(yearDropdown);
        selectYear.selectByVisibleText("2025");

        // Select the month
        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText("March");

        // Click the day 25
        WebElement day = driver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--025']"));
        day.click();	


	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
//		driver.close();
	}
}
