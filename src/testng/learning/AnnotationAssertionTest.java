package testng.learning;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import extent.ExtentManager;

public class AnnotationAssertionTest {
	
	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest test;
	
	@BeforeSuite
	public void beforeSuiteTest(){
		System.out.println("=========Before Suite=========");
	}
	
	@AfterSuite
	public void afterSuiteTest(){
		System.out.println("===========After Suite===========");
	}
	
	@BeforeTest // will execute before executing any test case
	public void beforeTest(){
		System.out.println("BEFORE executing the test cases");
	}
	
	@AfterTest  // will execute after executing all test case
	public void afterTest(){
		System.out.println("AFTER executing the test cases");
	}
	
	@BeforeMethod
	public void beforeMethodTest(){
		System.out.println("---Before Method");
	}
	
	@AfterMethod
	public void afterMethodTest(){
		System.out.println("-----After Method");
		extent.endTest(test);
		extent.flush();
	}

	@Test(priority=1) // makes this function a testcase
	public void doLoginTest(){
		
		test = extent.startTest("LOGIN TEST");
		test.log(LogStatus.SKIP, "Do Not Execute this test");
		System.out.println("LOGIN TEST");
		throw new SkipException("Do Not excute");
	}
	
	@Test(priority=2,dependsOnMethods={"doLoginTest"})
	public void changePasswordTest(){
		System.out.println("CHANGE PASSWORD");
	}
	
	@Test(priority=3,dependsOnMethods={"doLoginTest"})
	public void logoutTest(){
		System.out.println("LOGOUT TEST");
	}
	
	@Test(priority=4)
	public void skipTest(){
		test = extent.startTest("SKIP TEST");
		test.log(LogStatus.SKIP, "Do Not Execute this test");
		System.out.println("Skipping the test case");
		throw new SkipException("THIS test cases is skipped");
	}
	
	@Test(priority=5)
	public void assertTest(){
		
		test = extent.startTest("ASSERT TEST");
		test.log(LogStatus.INFO, "ASSERT TEST STARTED");
		System.out.println("Start");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals("actual", "expected");
		System.out.println("Continue after assertion");
		softAssert.fail("error message reported for failure");
		System.out.println("Continue after second failure");
		System.out.println("collect all assertions and print at the end");
		
		try{
			softAssert.assertAll();
		} catch(Throwable t){
			test.log(LogStatus.FAIL, "TEST Failed");		
			Assert.fail(t.getMessage());
		}
	}
	
	@Test
	public void positiveTest(){
		
		test = extent.startTest("POSITIVE TEST STARTING");
		System.out.println("Starting positive test");
		test.log(LogStatus.INFO,"Positive Test started executing");
		test.log(LogStatus.PASS, "Test Passed");
		
	}
	
	
}
