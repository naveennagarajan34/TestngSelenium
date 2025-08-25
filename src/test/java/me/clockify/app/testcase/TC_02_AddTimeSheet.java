package me.clockify.app.testcase;

import me.clockify.app.BaseClass;
import me.clockify.app.pages.TimeTrackerPage;

public class TC_02_AddTimeSheet extends BaseClass {
	public void verifyAddTimeSheet() {
		TimeTrackerPage timeObj = new TimeTrackerPage(driver);
		timeObj.navigateToTimeTrackerPage();
	}
}
