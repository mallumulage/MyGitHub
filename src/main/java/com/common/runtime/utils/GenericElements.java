package com.common.runtime.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.common.runtime.configure.BrowserSelection;

public class GenericElements extends BrowserSelection{
	public  Logger objLog = Logger.getLogger("GenericElements.class");
	public boolean blnVisibilityStatus;
	
	
	/*for input value*/
	public boolean inputText(By elementLocator, String value) {

		try {
			blnVisibilityStatus = verifyElementVisibility(elementLocator);
			if (blnVisibilityStatus) {
				highlightWebElement(elementLocator);
				dr.findElement(elementLocator).sendKeys(value);
				objLog.info(
						"The text " + value + " has been entered for the element " + elementLocator + " successfully.");
				return true;
			}
		} catch (Exception e) {
			objLog.warn("The text " + value + " could not be entered for the element " + elementLocator + " because "
					+ getStackTrace(e));
		}
		return false;
	}

	
	public String getText(By objElementLocator) {
		String strValue = null;
		try {
			blnVisibilityStatus = verifyElementVisibility(objElementLocator);
			if (blnVisibilityStatus) {
				strValue = dr.findElement(objElementLocator).getText();
			}
			objLog.info("The text "+"\""+strValue+"\""+" from the element "+"\""+objElementLocator+"\""+" is retrieved");
			return strValue;
		} catch (NoSuchElementException e) {
			objLog.warn("The text "+"\""+strValue+"\""+" from the element " + objElementLocator
					+ " is retrieved because of the Exception " + e.getMessage());
		e.printStackTrace();
		}
		return null;
	}
	public String getStackTrace(Exception e) {
		StringWriter objstrWriter = new StringWriter();
		PrintWriter objprintWriter = new PrintWriter(objstrWriter);
		e.printStackTrace(objprintWriter);
		return e.getMessage();
	}

	public boolean verifyElementVisibility(By objElementLocator) {
		try {
			blnVisibilityStatus = dr.findElement(objElementLocator).isDisplayed();
			if (blnVisibilityStatus) {
				objLog.info("Element "+"\""+objElementLocator+"\""+" is visible");
				return true;
			}
		} catch (Exception e) {
			objLog.warn("The Element for "+"\""+objElementLocator+"\""+" is not visible");
			e.printStackTrace();
		}
		return false;
	}

	public boolean highlightWebElement(By objElementLocator) {
		try {
			WebElement objElement = dr.findElement(objElementLocator);
			JavascriptExecutor js = (JavascriptExecutor) dr;
			js.executeScript("arguments[0].style.border='3px dotted purple'", objElement);
			Thread.sleep(200);
			js.executeScript("arguments[0].style.border='0px'", objElement);
			return true;
		} catch (Exception e) {
			objLog.warn("The WebElement "+"\""+objElementLocator+"\""+" has not been highlighted because " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}


	public boolean verifyElementPresent(By objElementLocator) {
		try {
			dr.findElement(objElementLocator);
			objLog.info("Element " + objElementLocator + " is present in DOM");
			return true;
		} catch (Exception e) {
			objLog.warn("Element " + objElementLocator + " is not present in DOM because " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean clickElement(By objElementLocator) {
		try {
			blnVisibilityStatus = verifyElementVisibility(objElementLocator);
			if (blnVisibilityStatus) {
				highlightWebElement(objElementLocator);
				dr.findElement(objElementLocator).click();
				objLog.info(" Element: "+objElementLocator);
				return true;
			}
		} catch (NoSuchElementException e) {
				objLog.warn(" Element: "+objElementLocator);
				e.printStackTrace();
		}
		return false;
	}

}