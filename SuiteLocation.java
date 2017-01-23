package com.ingenico.testsuite.location;

/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SuiteLocation.java $
$Id: SuiteLocation.java 17260 2016-02-25 05:39:06Z jsamuel $
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * 
 * Common Methods and variables  for location suite base 
 *
 */
public class SuiteLocation extends TestBase {
	/**
	 * Common variables for location suite
	 */
	public final static String EPAYMENT_LOC="Epayment Location",LOCCOLNAME="Location Name",AXIS_LOC="Add Axis location",
			TESTAXISLOC="Test Axis location : " ;


	//TODO****Framework Related Functions****

	/** 
	 * Initializes suite execution	 
	 */
	@BeforeSuite(alwaysRun=true)
	void initSetUp()  {
		initialize();

	}	

	/**
	 * Method to Create Axis Location
	 * @param webElement
	 * @param text
	 * @param poplocs
	 * @param popvlas
	 */
	public void createAxisLocation(WebElement webElement,String text,String[] poplocs,String[] popvlas){
		selUtils.clickOnWebElement(selUtils.getCommonObject("plusbtn_xpath"));
		setValsForPopUpWin(webElement,text,poplocs,popvlas);
		clk1chainAxisServ(testDataOR.get("one_chain"),"onechain_chkbx_id");
		/*if(testDataOR.get("one_chain").equalsIgnoreCase("true")){
			selUtils.slctChkBoxOrRadio(selUtils.getObject("onechain_chkbx_id"));
		}*/
		clk1chainAxisServ(testDataOR.get("use_axis_services"),"use_axisservice_id");
		selUtils.clickOnWebElement(selUtils.getCommonObject("okbttn_xpath"));
		Assert.assertFalse(waitMethods.isElementPresent("axislocerror_xpath"),"error while adding Axis location");
		waitMethods.waitForWebElementPresent(selUtils.getCommonObject("msg_xpath"));
		logger.info("Axis Location with name '"+popvlas[0]+"' successfully added");
	}
	
	/**
	 * Method to check one chain and use axis service chkbox
	 * @param testdataor
	 * @param ckbxloc
	 */
	public void clk1chainAxisServ(String testdataor,String ckbxloc)
	{
		if(testdataor.equalsIgnoreCase("true")){
			selUtils.slctChkBoxOrRadio(selUtils.getObject(ckbxloc));
		}	
	}



	/**
	 * Method to verify test connection
	 * @param locator
	 * @param axisloc
	 * @param sshtext
	 * @param dbtext 
	 */	
	public void clickOnTestConBttn(final String locator, final String axisloc,final String sshtext,final String dbtext){


		xpath=getPath(locator).replace("NAME", axisloc);
		if(selUtils.getObjectDirect(By.xpath(xpath)).isDisplayed()){
			selUtils.getObjectDirect(By.xpath(xpath)).click();
			if(selUtils.getObject("axishost_err_id").getAttribute("class").contains("divErrorPopup"))
			{
				Assert.fail("Problem with axis location connection");	
				logger.info("Problem with axis location connection");
			}	

			else 
				if(getModWinDisp(selUtils.getObject("axispuptit_id"), TESTAXISLOC+axisloc))
				{
					waitMethods.waitForWebElementPresent(selUtils.getObject("sshresponsetext_xpath"));
					selUtils.verifyTextContains(selUtils.getObject("sshresponsetext_xpath"), sshtext);
					selUtils.verifyTextContains(selUtils.getObject("dbresponsetext_xpath"), dbtext);
					logger.info("Verified successfully the test connection message");
					selUtils.getObject("closebttn_xpath").click();
					logger.info("Clicked on close button");
				}
				else{
					logger.info("Modal Window title is not matching with "+TESTAXISLOC+axisloc);
					Assert.fail("Modal Window title is not matching with "+TESTAXISLOC+axisloc);
					
				}

		}
	}
}




