package me.clockify.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	public void navigateToTimeTrackerPage() {
		timeTrackerNavigateMenu.click();
	}
	
	public void enterTaskWorkedOn(String taskWorked) {
		workedOnTasksInputBox.sendKeys(taskWorked);
	}
	
	public void enterStartAndEndTime(String start, String end) {
		taskStartTimeInputBox.sendKeys(start);
		taskEndTimeInputBox.sendKeys(end);
	}

	public void selectProject(String projectName) {
		selectProjectInput.sendKeys(projectName);
		WebElement project = driver.findElement(By.xpath("//section//button[contains(text(),'" + projectName + "')]"));
		project.click();
	}
}
