package com.ingenico.testsuite.crosschannel;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/crosschannel/SMR1167.java $
$Id: SMR1167.java 18288 2016-04-28 05:35:43Z rkahreddyga $
 */

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ingenico.common.CommonConstants;

/**
 * SMR-1167:Crosschannel subscription
 * @author Hariprasad.KS 
 *
 */
public class SMR1167 extends CrossChannel{

	/**
	 * Crosschannel subscription
	 */
	@Test(groups="SMR1167")
	public void smr1167() {

		try{
			eportalCust=testDataOR.get("customer");
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name"); 
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1167 execution started");
			final String custName=testDataOR.get("customer");
			String customerID;

			//Access Everest with customer
			//Go to Customer module in everest,select customer
			// uncheck 'Subscription to UCI' box and save it
			logger.info("Step 1, 2:");
			navigateToCustomerPage();
			clkCustNameList(custName);
			selUtils.verifyEltEnabled(selUtils.getObject("producttoken_id"));
			selUtils.unSlctChkBoxOrRadio(selUtils.getObject("producttoken_id"));
			selUtils.clickOnWebElement(selUtils.getObject("save1_id"));
			logger.info("Unchecked 'Subscription to UCI' box and saved");

			//verify 'Subscription to Cross Channel' and 'Subscription to UCI' boxes are unchecked
			vEleNotSelec("producttoken_id", "prodcrosschannel_id");
			logger.info("'Subscription to Cross Channel' and 'Subscription to UCI' boxes are unchecked");
			//read customer id
			customerID=selUtils.getObject("custid_xpath").getText().trim();

			//check the 'Subscription to UCI' and 'Subscription to Cross Channel' boxes 
			//and verify success message
			logger.info("Step 3:");
			selCheckNval("producttoken_xpath", "prodcrosschannel_xpath");
			logger.info("'Subscription to UCI' and 'Subscription to Cross Channel' boxes are checked and verified success message");

			//DB validation 
			if(dbCheck){
				sqlQuery = "SELECT product_cross_channel,product_token FROM everest_customer WHERE party_id='"+customerID+"'";
				resSet = dbMethods.getDataBaseVal(testDataOR.get("databaseEverest"),sqlQuery,CommonConstants.ONEMIN);
				Assert.assertTrue(resSet.getString("product_cross_channel").equalsIgnoreCase("t"), "product_cross_channel not set to TRUE in the database");
				Assert.assertTrue(resSet.getString("product_token").equalsIgnoreCase("t"), "product_token not set to TRUE in the database");
				logger.info("product_cross_channel and product_token are set to TRUE in the database");
			}

			//Navigate to eportal module and select customer
			//The 'KPI' and 'Unified Journal' boxes are unchecked
			logger.info("Step 4:");
			selUtils.clickOnWebElement(selUtils.getCommonObject("eportal_tab_link"));
			selUtils.selectItem(selUtils.getCommonObject("cust_Sel_id"), custName);
			logger.info("Navigated to eportal module and selected customer "+custName);
			vEleNotSelec("kpimodule_id", "unifjoumodule_id");
			logger.info("The 'KPI' and 'Unified Journal' boxes are unchecked");

			//check the 'KPI' and 'Unified Journal' boxes then verify message of success
			logger.info("Step 5:");
			selCheckNval("kpimodule_id", "unifjoumodule_id");
			logger.info("'KPI' and 'Unified Journal' boxes are checked and verified success message");

			//Access ePortal with a superuser and  select customer	
			logger.info("Step 6 :");
			logoutEpSelCust(custName);
			//verify 'Multi-Channel' menu has been added with access to the 'Journal' and 'Key Indicators' sub-menus.
			selUtils.verifyElementDisp(selUtils.getCommonObject("multichannel_xpath"), MULTICHANNEL);
			vSubModPresent(JORNAL,selUtils.getCommonObject("multichannel_xpath"),"multichajour_xpath");
			vSubModPresent(KEYINDECATOR,selUtils.getCommonObject("multichannel_xpath"),"multikeyindec_xpath");

			//Go to 'User Management' and Click on 'Add user'
			//verify 'Transaction Management' checkbox is displayed in the 'Payment Rights' section.
			logger.info("Step 7 :");
			selUtils.clickOnWebElement(selUtils.getCommonObject("usermangement_link"));
			logger.info("Clicked on User management tab");
			selUtils.switchToFrame();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			selUtils.jScriptClick(selUtils.getObject("adduserbttn_xpath"));
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			clkOnModRtsPlsBttn("expand_pmtrights_xpath","pmtrights_plsbttn_xpath");
			selUtils.verifyElementDisp(selUtils.getObject("transmanagemod_xpath"), "Transaction Management");

			logger.info("SMR1167 Executed Successfully");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
	
	/**
	 * Select check boxes and validate success message	
	 * @param locator
	 * @param locatorAno
	 */
	private void selCheckNval(String locator, String locatorAno)
	{
		selUtils.slctChkBoxOrRadio(selUtils.getObject(locator));
		selUtils.slctChkBoxOrRadio(selUtils.getObject(locatorAno));
		//selUtils.clickOnWebElement(selUtils.getCommonObject("savebttn_xpath"));
	    selUtils.jScriptClick(selUtils.getCommonObject("savebttn_xpath"));
		logger.info("Clicked on Save button");
		Assert.assertTrue(selUtils.getCommonObject("msg_xpath").getText().contains(SUCCFULLYUPDT),SUCCFULLYUPDT+" success message does not appear");
	}
	/**
	 * validate element not selected
	 * @param locator
	 * @param locatorAno
	 */
	private void vEleNotSelec(String locator, String locatorAno)
	{
		selUtils.verifyElementNotSelected(selUtils.getObject(locator));
		selUtils.verifyElementNotSelected(selUtils.getObject(locatorAno));
	}

}

