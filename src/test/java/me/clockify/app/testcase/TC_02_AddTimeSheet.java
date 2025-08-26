package me.clockify.app.testcase;

import java.util.List;

import org.testng.annotations.Test;

import me.clockify.app.BaseClass;
import me.clockify.app.pages.TimeTrackerPage;
import me.clockify.app.utilities.ExcelUtility;
import me.clockify.app.utilities.LoginUtility;

public class TC_02_AddTimeSheet extends BaseClass {

	public void verifyAddTimeSheet() {
		if (!driver.getCurrentUrl().contains("clockify")) {
			driver.get("https://app.clockify.me/");
		}

		if (!LoginUtility.isLoggedIn(driver)) {
			LoginUtility.performLogin(driver, "naveen.n@ecgroup-intl.com", "naveT23LMN");
		}

		TimeTrackerPage timeObj = new TimeTrackerPage(driver);
		timeObj.navigateToTimeTrackerPage();
		timeObj.enterTaskWorkedOn("Regression Testing...");
		timeObj.selectProject("ScriptureForge");
	}

	@Test
	public void verifyAddTimeSheetTest() {

		if (!driver.getCurrentUrl().contains("clockify")) {
			driver.get("https://app.clockify.me/");
		}

		if (!LoginUtility.isLoggedIn(driver)) {
			LoginUtility.performLogin(driver, "naveen.n@ecgroup-intl.com", "naveT23LMN");
		}

		// Read data from Excel
		List<String[]> timesheetData = ExcelUtility.readTimesheetData("src/test/resources/testdata/timesheets.xlsx");

		if (timesheetData.isEmpty()) {
			System.out.println("No timesheet data found in Excel file.");
			return;
		}

		System.out.println("\nHeader: Task | Start Time | End Time | Project");
		System.out.println("----------------------------------------------");
		int rowCount = 1;
		TimeTrackerPage timeObj = new TimeTrackerPage(driver);

		timeObj.navigateToTimeTrackerPage();
		for (String[] rowData : timesheetData) {
			String startTime = rowData[0];
			String endTime = rowData[1];
			String project = rowData[2];
			String task = rowData[3];
			// Skip if essential data is missing
			if (task.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
				System.out.println("Row " + rowCount + ": SKIPPED (Missing essential data)");
				rowCount++;
				continue;
			}
			System.out.println(task);
			timeObj.enterTaskWorkedOn(task);
			timeObj.selectProject(project);
			timeObj.enterStartAndEndTime(startTime, endTime);
			timeObj.selectDate("25/08/2025");
			timeObj.clickAddBtn();
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}