package com.ingenico.testsuite.location;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SMR1936.java $
$Id: SMR1936.java 18412 2016-05-04 09:56:26Z jsamuel $
 */

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *  SMR-1936:Edit an Axis Location already provisioned
 * @author joel.samuel
 *
 */
public class SMR1936  extends SuiteLocation{

	/**
	 * Edit an Axis Location already provisioned
	 */

	@Test(groups="SMR1936")
	public void smr1936() {

		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1936 execution started");	
			String axisLocation=testDataOR.get("axis_location");

			//Access Everest with a superuser
			//Go to "Card Payment - Axis location" sub menu			
			logger.info("Step 1, 2:");
			navigateToSubPage(AXISLOC,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("axisloc_xpath"));
			logger.info("Navigated to Card Payment - Axis location page");
			
            //click on edit button of axis_location
			logger.info("Step 3:");
			clkOnDirectObj("axislocedit_xpath", NAME, axisLocation);
			Assert.assertFalse(selUtils.getObject("locationname_id").isEnabled(),"axis location name field is  editable");
			logger.info("axis location name field is not  editable");
			
			//click on close button of edit window
			logger.info("Step 4:");
			selUtils.clickOnWebElement(selUtils.getObject("close_editloc_xpath"));
			logger.info("clicked on close button");
			
			
			logger.info("SMR1936 is successfully executed");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
}