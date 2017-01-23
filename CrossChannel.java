package com.ingenico.testsuite.crosschannel;

/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/crosschannel/CrossChannel.java $
$Id: CrossChannel.java 18283 2016-04-27 14:46:16Z rkahreddyga $
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Common methods and variables of Ogone suite
 */
public class CrossChannel extends TestBase {
	//Declaration of common variables
	//public final static String SUCCFLYUPDATED="successfully updated";

	//Framework Related Functions

	/** 
	 * Initializes suite execution
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	@BeforeSuite(alwaysRun=true)
	void initSetUp()   {
		initialize();
	}
	/**
	 * Method for selecting the check box or radio items
	 * @param element
	 */
	public void selChkBoxOrRadio(WebElement element)
	{
		try{
			if(element.isSelected())
			{
				logger.info("Checkbox/Radio is already selected by default");			
			}
			else
			{
				logger.info("Before clicking");
				//((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				waitMethods.waitForWebElementPresent(element);
				element.click();
				if(!element.isSelected())
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					logger.info("JS Clicked on checkbox/radio");
				}
				
				logger.info("Clicked on checkbox/radio");
			}
		}catch(Exception e){
			Assert.fail("Failed while selecting a checkbox");
		}
	}
}