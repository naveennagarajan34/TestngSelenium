package me.clockify.app.pages;

import java.awt.RenderingHints.Key;
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

	public void navigateToTimeTrackerPage() {
		timeTrackerNavigateMenu.click();
	}

	public void enterTaskWorkedOn(String taskWorked) {
		workedOnTasksInputBox.sendKeys(taskWorked);
	}

	public void enterStartAndEndTime(String start, String end) {
//		taskStartTimeInputBox.clear();
//		taskEndTimeInputBox.clear();

		System.out.println(start + " " + end);
		Actions actions = new Actions(driver);

		actions.moveToElement(taskStartTimeInputBox).click().perform();

		taskStartTimeInputBox.sendKeys(start);
		actions.moveToElement(taskEndTimeInputBox).click().perform();
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
		dateInput.clear();
		dateInput.sendKeys(date);
	}

	public void clickAddBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(addEntryButton)).click();
	}
}
