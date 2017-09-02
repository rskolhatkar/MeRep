package testng.learning;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import extent.ExtentManager;

public class ParameterizationTest {
	
	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest test;
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("Before execution of the test");
	}

	@AfterTest
	public void afterTest(){
		System.out.println("After execution of the test");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("Before execution of the test method");
	}

	@AfterMethod
	public void afterMethod(){
		System.out.println("After execution of the test method");
	}

	@Test(dataProvider="getData")
	public void doLogin(String firstName, String lastName, int dateOfBirth){
		System.out.println(firstName + " : " + lastName + " : " + dateOfBirth);
	}
	
	@DataProvider
	public Object[][] getData(){
		
		int rows=2; // number of repetitions
		int cols=3; // number of parameters
		Object[][] data = new Object[rows][cols];
		
		data[0][0] = "raghav";
		data[0][1] = "kolhatkar";
		data[0][2] = 26101981;
		
		data[1][0] = "priyanka";
		data[1][1] = "bhide";
		data[1][2] = 22121985;
		
		return data;
	}

}
