package com.ingenico.testsuite.location;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SMR1932.java $
$Id: SMR1932.java 18259 2016-04-26 12:13:24Z haripraks $
 */

import org.testng.annotations.Test;

/**
 * SMR-1932:Create an already axis location
 * @author Hariprasad.KS
 *
 */
public class SMR1932  extends SuiteLocation{

	/**
	 * Create an already axis location
	 */

	@Test(groups="SMR1932")
	public void smr1932() {

		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1932 execution started");	
			String axisLocation=testDataOR.get("axis_location");

			//Access Everest with a superuser
			//Go to "Card Payment - Axis location" sub menu			
			logger.info("Step 1, 2:");
			navigateToSubPage(AXISLOC,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("axisloc_xpath"));
			waitMethods.waitForWebElementPresent(selUtils.getCommonObject("plusbtn_xpath"));
			logger.info("Navigated to Card Payment - Axis location page");

			//Create the duplicate Axis location and verify error message			 
			logger.info("Step 3:");
			selUtils.clickOnWebElement(selUtils.getCommonObject("plusbtn_xpath"));
			selUtils.populateInputBox("locationname_id",axisLocation);
			selUtils.clickOnWebElement(selUtils.getCommonObject("okbttn_xpath"));
			selUtils.verifyTextContains(selUtils.getCommonObject("axiserror_xpath"), axisLocation);
			logger.info("Verified error message for the duplicate Axis location "+axisLocation);

			//Close the Add window	 
			logger.info("Step 4:");
			selUtils.clickOnWebElement(selUtils.getObject("axislocclose_xpath"));
			logger.info("Cicked on close button");

			logger.info("SMR1932 execution successful");
		}
		catch (Throwable t) {
			handleException(t);
		}
	}

}
