package com.common.runtime.utils;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.common.runtime.configure.SelectDriver;
import com.common.runtime.configure.SelectTestData;
import com.common.runtime.utils.GenericElements;
import com.relevantcodes.extentreports.ExtentReports;

import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;

import bsh.ParseException;

public class ExtentReportListenerNG extends GenericElements implements ITestListener  {
	

 public static WebDriver driver;

 public static ExtentReports reports;

 public static ExtentTest test;
String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());


 public void onTestStart(ITestResult result) {

	
	 
  System.out.println("on test start");

  test = reports.startTest(result.getMethod().getMethodName());
 }

 public void onTestSuccess(ITestResult result) {

  System.out.println("on test success");
  try {
  if(result.getStatus()==ITestResult.SUCCESS)
	  {
test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");

 }
  }
  catch(Exception e)
  {
	  e.printStackTrace();
  }
 } 

public void onTestFailure(ITestResult result) {

  System.out.println("on test failure");

  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");

  

  try {
	  
	test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
	  

  } catch (Exception e) {

   e.printStackTrace();

  }

 }

 public void onTestSkipped(ITestResult result) {

  System.out.println("on test skipped");

  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");

 }

 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  System.out.println("on test sucess within percentage");

 }

 public void onStart(ITestContext context) {
try
{
  System.out.println("on start");
 System.setProperty("webdriver.chrome.driver",SelectDriver.gstrCHROME_DRIVER_PATH);
  driver = new ChromeDriver(); // Set the drivers path in environment variables to avoid code(System.setProperty())

 
	  reports = new ExtentReports(System.getProperty("user.dir")+"\\TestReports\\Reports\\Report"+timeStamp +".html");
	
  }
catch(Exception e)
{
	e.printStackTrace();
}
 }

 public void onFinish(ITestContext context) {

  System.out.println("on finish");

  driver.close();

  reports.endTest(test);

  reports.flush();

 }

 
}