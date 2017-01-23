package com.ingenico.testsuite.crosschannel;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/crosschannel/SMR9.java $
$Id: SMR9.java 17775 2016-03-30 11:55:51Z haripraks $
 */

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * SMR-9:Online subscription
 * @author Joel.Samuel
 *
 */
public class SMR9 extends CrossChannel{

	/**
	 * Online subscription
	 * @param browser
	 */
	@Test(groups="SMR9")
	public void smr9() {

		try{
			final String firstName=testDataOR.get("multi_first_name"),lastName=testDataOR.get("multi_last_name"); 
			login("URLEverest",testDataOR.get("multi_user_login"),firstName,lastName);
			logger.info("SMR9 execution started");
			final String custName=testDataOR.get("customer");
			
			//Access Everest with multi_user login
			//Go to eportal menu in everest,select customer
			logger.info("Step 1, 2:");
			clkOnEpTbNselCus(custName);
			selUtils.verifyElementNotSelected(selUtils.getObject("onlpymt_ckbx_id"));
			
			//Go to "Customer" module and select customer
			logger.info("Step 3:");
			selUtils.getCommonObject("cust_tab_link").click();
			//clkOnElement("page_id","custname_xpath",custName);
			colIndex=selUtils.getIndexForColHeader("gprscolheader_css", NAMECOL);
			verifyLvlColLvlValPresence("entitytablelst_css",colIndex,custName);
			clkOnDirectObj("custname_xpath", "NAME", custName);
			selUtils.verifyElementNotSelected(selUtils.getObject("prdtonlinepymt_ckbx_id"));
			
			// Check the "Subscription to Online Payment" box and save
			logger.info("Step 4:");
			selUtils.slctChkBoxOrRadio(selUtils.getObject("prdtonlinepymt_ckbx_id"));
			selUtils.getCommonObject("savebttn_xpath").click();
			Assert.assertTrue(selUtils.getCommonObject("msg_xpath").getText().contains(SUCCFULLYUPDT),"failed while validating "+SUCCFULLYUPDT+" message display");
			logger.info("Verified success message");
			
			//Go to the "ePortal" menu in Everest and select customer
			logger.info("Step 5:");
			clkOnEpTbNselCus(custName);
			selUtils.verifyElementSelected(selUtils.getObject("onlpymt_ckbx_id"));

			logger.info("SMR9 Executed Successfully");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
	
	/**
	 * Method to go to eportal tab and sel cust
	 */
	private void clkOnEpTbNselCus(String customer){
		selUtils.clickOnWebElement(selUtils.getCommonObject("eportal_tab_link"));
		selUtils.selectItem(selUtils.getCommonObject("cust_Sel_id"), customer);
	}
}

