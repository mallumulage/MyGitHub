package com.common.runtime.configure;

import java.io.BufferedReader;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;



public class BrowserSelection {
	public static  WebDriver dr;
	
	
	
	public WebDriver initialization(){
		
		if(dr==null)
		{
		if(SelectTestData.gstrBrowserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions objOptions = new ChromeOptions();
			objOptions.addArguments("--test-type");
			objOptions.addArguments("chrome.switches", "--disable-extensions");
			objOptions.addArguments("start-maximized");
			objOptions.addArguments("disable-infobars");
			Map<String, Object> mpPrefs = new HashMap<String, Object>();
			mpPrefs.put("credentials_enable_service", false);
			mpPrefs.put("password_manager_enabled", false);
			objOptions.setExperimentalOption("prefs", mpPrefs);
			System.setProperty("webdriver.chrome.driver", SelectDriver.gstrCHROME_DRIVER_PATH);
			dr = new ChromeDriver(objOptions);
			}
		
		else if(SelectTestData.gstrBrowserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",SelectDriver.gstrFIREFOX_DRIVER_PATH);
			dr = new FirefoxDriver();
		}
		else if (SelectTestData.gstrBrowserName.equalsIgnoreCase("safari")) {
			dr = new SafariDriver();
		}
		else {
			System.setProperty("webdriver.ie.driver", SelectDriver.gstrIE_DRIVER_PATH);
			dr = new InternetExplorerDriver();
		}
		}
		dr.manage().window().maximize();
	//	dr.manage().deleteAllCookies();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 		dr.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
 		dr.get(SelectTestData.springerLink);
 		
		return dr;
		}
	
	public static void quit()
	{
		dr.quit();
		dr=null;
	}
	
	public static void close()
	{
		dr.close();
		dr = null;
	}
}
