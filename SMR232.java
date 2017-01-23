package com.ingenico.testsuite.assettracking;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/assettracking/SMR232.java $
$Id: SMR232.java 16708 2016-01-20 09:53:29Z rjadhav $
 */


import org.testng.annotations.Test;

/**
 * SMR-232:User without Asset Tracking
 * @author Joel.Samuel
 *
 */
public class SMR232 extends SuiteAssetTracking{

	/**
	 * Testing a user with NO asset tracking rights.
	 * @param browser
	 */
	@Test(groups="SMR232")
	public void smr232() {
		try{
			
			eportalCust=testDataOR.get("customer");
			final String multiuser=testDataOR.get("multi_user_login"),firstName=testDataOR.get("multi_first_name"),lastName=testDataOR.get("multi_last_name");
			logger.info("SMR232 execution started");
			logger.info("Step 1:");
			login("URLEportal",multiuser,firstName,lastName);
			
			//Go to "TMS" tab,Asset tracking should not be available
			logger.info("Step 2:");
			vSubModNotPresent(ASSETTKING,selUtils.getCommonObject("tms_tab_xpath"),"tms_assttrack_xpath");

			logger.info("SMR232 Executed Successfully");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}

}

