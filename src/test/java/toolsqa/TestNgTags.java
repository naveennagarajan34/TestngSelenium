package toolsqa;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgTags {

	@BeforeSuite
	public void method_1() {
		System.out.println("Before Suite");
	}

	@BeforeClass
	public void method_2() {
		System.out.println("Before Class");
	}

	@BeforeTest
	public void method_3() {
		System.out.println("Before Test");
	}

	@Test
	public void method_4() {
		System.out.println("Test_A");
	}

	@BeforeMethod
	public void method_5() {
		System.out.println("BeforeMethod");
	}

	@AfterClass
	public void method_6() {
		System.out.println("After Class");
	}


	@AfterMethod
	public void method_8() {
		System.out.println("Test_B");
	}


	@AfterTest
	public void method_7() {
		System.out.println("After test");
	}

	@AfterGroups
	public void method_9() {
		System.out.println("After Groups");
	}

	// priority is 0 by default
	// priority execute from lower to higher
	// if multiple same priorities then executed based on method name in ascending order
	
	@Test(priority=-1)
	public void method_10() {
		System.out.println("Test_E");
	}

}
