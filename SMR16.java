package com.ingenico.testsuite.location;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SMR16.java $
$Id: SMR16.java 18251 2016-04-26 09:19:11Z haripraks $
 */

import org.testng.annotations.Test;

/**
 * SMR-16:Create Axis Location
 * @author Joel.Samuel
 *
 */
public class SMR16  extends SuiteLocation{
	
	private final static String[] AXISLOCFIELDS={"locationname_id"};

	@Test(groups="SMR16")
	/**
	 * Create an Axis Location and checking the creation on the other components
	 * @param browser
	 */
	public void smr16() {

		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR16 execution started");	
			final String sshRespMess="Axis location successfully reached with SSH!",dbRespMess="Axis location Database successfully reached!",
			axisLocation=testDataOR.get("axis_location");
			
			final String[] axisLocPopUpVals={axisLocation};
			
			//Access Everest with a superuser
			//Go to "Card Payment >> Axis location" sub menu			
			logger.info("Step 1, 2:");
			navigateToSubPage(AXISLOC,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("axisloc_xpath"));
			waitMethods.waitForWebElementPresent(selUtils.getCommonObject("plusbtn_xpath"));
			
			//Create Axis location 			 
			logger.info("Step 3:");
			//verifyExistingData("delloc_bttn_xpath","colheaders_css","colallrows_xpath",axisLocation,LOCCOLNAME);
			createAxisLocation(selUtils.getCommonObject("popup_wintitle_id"),AXIS_LOC,AXISLOCFIELDS,axisLocPopUpVals);
			
			//Verify axis location presence of the created data
			colIndex=selUtils.getIndexForColHeader("colheaders_css", LOCCOLNAME);
			verifyLvlColLvlValPresence("entitytablelst_css",colIndex,axisLocation);
			
			//Click the Test connection button and verify the message			 
			logger.info("Step 4:");
			clickOnTestConBttn("testconn_bttn_xpath",testDataOR.get("axis_location"),sshRespMess,dbRespMess);
			
			logger.info("SMR16 execution successful");
			
		}
		catch (Throwable t) {
			handleException(t);
		}
	}

}
