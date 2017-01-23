package com.ingenico.testsuite.location;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SMR1937.java $
$Id: SMR1937.java 18313 2016-04-28 12:07:30Z rjadhav $
 */

import org.testng.annotations.Test;

/**
 * SMR-1937:Delete Axis Location button must not be displayed
 * @author Suresh Kumar TV
 *
 */
public class SMR1937  extends SuiteLocation{

	/**
	 * Delete Axis Location button must not be displayed
	 */

	@Test(groups="SMR1937")
	public void smr1937() {

		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1937 execution started");	
			String axisLocation=testDataOR.get("axis_location");

			//Access Everest with a superuser
			//Go to "Card Payment - Axis location" sub menu			
			logger.info("Step 1, 2:");
			navigateToSubPage(AXISLOC,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("axisloc_xpath"));
			logger.info("Navigated to Card Payment - Axis location page");
			
			logger.info("Step 3:");
			xpath=getPath("deleteaxisloc_xpath").replace(NAME, axisLocation);
			assertFalse(selUtils.isElementPresentxpath(xpath));
			
			logger.info("SMR1937 is successfully executed");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
}