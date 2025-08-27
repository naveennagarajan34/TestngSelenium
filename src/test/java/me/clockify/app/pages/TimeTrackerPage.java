package me.clockify.app.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeTrackerPage extends BasePage {

	public TimeTrackerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "app-sidebar-navigation-item a[href='/tracker']")
	WebElement timeTrackerNavigateMenu;
	@FindBy(css = "time-tracker-recorder input[name='autocomplete-input']")
	WebElement workedOnTasksInputBox;
	@FindBy(css = "time-tracker-recorder input-time-ampm[data-cy='startTime'] input")
	WebElement taskStartTimeInputBox;
	@FindBy(css = "time-tracker-recorder input-time-ampm[data-cy='endTime'] input")
	WebElement taskEndTimeInputBox;
	@FindBy(css = "time-tracker-recorder div[dropdownkeybutton]")
	WebElement selectProjectLink;
	@FindBy(css = "project-picker input")
	WebElement selectProjectInput;
	@FindBy(css = "div.cl-input-date-picker-container input")
	WebElement dateInput;
	@FindBy(xpath = "//div[@data-cy='add-btn']")
	WebElement addEntryButton;
	@FindBy(css = "div#toast-container")
	WebElement snackBarToast;

	public void navigateToTimeTrackerPage() {
		timeTrackerNavigateMenu.click();
	}

	public void enterTaskWorkedOn(String taskWorked) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(workedOnTasksInputBox)).click();
		workedOnTasksInputBox.sendKeys(taskWorked);
	}

	public void enterStartAndEndTime(String start, String end) {
//		taskStartTimeInputBox.clear();
//		taskEndTimeInputBox.clear();

		System.out.println(start + " " + end);
		Actions actions = new Actions(driver);

		actions.moveToElement(taskStartTimeInputBox).click().perform();
		taskStartTimeInputBox.sendKeys(Keys.CONTROL + "a"); // Select all text
		taskStartTimeInputBox.sendKeys(Keys.DELETE); // Clear the selection
		taskStartTimeInputBox.sendKeys(start);

		actions.moveToElement(taskEndTimeInputBox).click().perform();
		taskEndTimeInputBox.sendKeys(Keys.CONTROL + "a");
		taskEndTimeInputBox.sendKeys(Keys.DELETE);
		taskEndTimeInputBox.sendKeys(end + Keys.ENTER);

	}

	public void selectProject(String projectName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(selectProjectLink)).click();
		selectProjectInput.sendKeys(projectName);
		WebElement project = driver.findElement(By.xpath("//section//button[contains(text(),'" + projectName + "')]"));
//		project.click();
		wait.until(ExpectedConditions.elementToBeClickable(project)).click();
	}

	public void selectDate(String date) {
		Actions actions = new Actions(driver);
		actions.moveToElement(dateInput).click().perform();
		dateInput.sendKeys(Keys.CONTROL + "a"); // Select all text
		dateInput.sendKeys(Keys.DELETE); // Clear the selection
		dateInput.sendKeys(date);
	}

	public void clickAddBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(addEntryButton)).click();
		wait.until(ExpectedConditions.visibilityOf(snackBarToast));
		wait.until(ExpectedConditions.invisibilityOf(snackBarToast));
	}
}
