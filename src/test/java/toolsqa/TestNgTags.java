package toolsqa;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
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

	@Test(groups = { "smoke" })
	public void method_4() {
		System.out.println("Test_A");
	}

	@BeforeMethod
	public void method_5() {
		System.out.println("BeforeMethod");
	}

	@BeforeGroups("regression")
	public void method_6() {
		System.out.println("Before Groups");
	}

	@Test(groups = { "smoke" })
	public void method_8() {
		System.out.println("Test_B");
	}

	@Test(groups = { "regression", "smoke" })
	public void method_7() {
		System.out.println("Test_C");
	}

	@Test(groups = { "smoke" })
	public void method_9() {
		System.out.println("Test_D");
	}

	@Test(groups = { "regression" })
	public void method_10() {
		System.out.println("Test_E");
	}

}
