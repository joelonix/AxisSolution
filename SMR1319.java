package com.ingenico.testsuite.tokenserver;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/tokenserver/SMR1319.java $
$Id: SMR1319.java 17374 2016-03-03 09:00:40Z amsingh $
 */

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * SMR-1319: Upload a Digest profile from a zip file
 *  for a customer with No subscription to UCI then
 *  for a customer with a subscription to UCI
 * 
 * @author Raghunath.K
 *
 */

public class SMR1319 extends SuiteTokenServer {

	/**
	 * Upload a Digest profile from a zip file for a customer
	 * @param browser
	 * 
	 */
	@Test(groups={"SMR1319"})
	public void smr1319() {
		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1319 execution started");	
			final String digestFldrNam="AQA_DIGEST",appProfileName=testDataOR.get("digest_profile"),
			custName=testDataOR.get("customer");
			
			profileCrt(digestFldrNam);
			profilezip(appProfileName,tempFldrNme);

			//Creating the Profiles.zip file
			zipProfiles();

			//GOTO to Card Payment-Profile and assign digest profile to the customer			
			logger.info("Step 1, 2:");
			vExistsNAddProfile(appProfileName, custName);
			
			logger.info("Step 3:");
			//click on edit digest profile
			waitNSec(2);
			clkOnDirectObj("editdigestprof_xpath","NAME",profileTxt);
			logger.info("Clicked on Edit profile button '"+profileTxt);

			//customer is not a member of the Assigned Customers
			xpath=getPath("notassigncust__xpath").replace("PROFILE",custName);
			Assert.assertFalse(selUtils.isElementPresentxpath(xpath),"Failed due to element not present");
			logger.info("Verified customer is not a member of the Assigned Customers");

			// Click on ok button of Digest Edit Profile
			logger.info("Step 4:");
			editDgtClickOk();
			logger.info("SMR1319 executed successfully");

		}catch (Throwable t) {
			handleException(t);
		}


	}
	

	/**
	 * Click on Edit Digest profile window ok button
	 * 
	 */
	private void editDgtClickOk()
	{ 
		try{		
			if(getModWinDisp(selUtils.getObject("editprofile__xpath"),EDITPROFILE)){
				selUtils.clickOnWebElement(selUtils.getObject("editprofileok__xpath"));
				logger.info(EDITPROFILE+" window has displayed and clicked on OK");//Edit profile assignment
			}
		}catch(Exception e){
			Assert.fail(EDITPROFILE+" window not displayed");//Edit profile assignment
		}

	}


}

