package com.ingenico.testsuite.tokenserver;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/tokenserver/SMR1269.java $
$Id: SMR1269.java 18030 2016-04-13 09:25:50Z jsamuel $
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ingenico.common.CommonConstants;

/**
 * SMR-1269:Token&Digest subscription
 * @author Joel F
 *
 */

public class SMR1269 extends SuiteTokenServer {

	/**
	 * Token&Digest subscription
	 * 
	 */
	@Test(groups={"SMR1269"})
	public void smr1269() {
		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			final String multiFirstName=testDataOR.get("multi_first_name"),multilastName=testDataOR.get("multi_last_name"),dbTokenServer=testDataOR.get("databaseTokenServer"),
			digestKey=testDataOR.get("digest_key"),tokenFormat=testDataOR.get("token_format"),projectName=testDataOR.get("project_name"),multiUserLogin=testDataOR.get("multi_user_login"),
			appProfileName=testDataOR.get("digest_profile"),userPswd=testDataOR.get("user_password");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			eportalCust=testDataOR.get("customer");
			logger.info("SMR1269 execution started");
			
			//Access Everest with a superuser and select customer in cust module 
			logger.info("Step 1,2 :");
			clkCustNameList(eportalCust);
			selUtils.unSlctChkBoxOrRadio(selUtils.getObject("cross_channelckbx_id"));
			selUtils.unSlctChkBoxOrRadio(selUtils.getObject("uci_ckbx_id"));
			selUtils.verifyElementNotSelected(selUtils.getObject("uci_ckbx_id"));
			logger.info("The subscription to UCI checkbox is unchecked");
			
			//Check the "Subscription to UCI" box,Click on the "Save" button
            //message of success appears
			logger.info("Step 3 :");
			selUtils.slctChkBoxOrRadio(selUtils.getObject("uci_ckbx_id"));
			selUtils.clickOnWebElement(selUtils.getObject("save_bbtn_id"));
			//Assert.assertTrue(selUtils.getCommonObject("msg_xpath").getText().contains(SUCCFULLYUPDT)," not updated successfully");
			selUtils.verifyTextContains(selUtils.getCommonObject("msg_xpath"), SUCCFULLYUPDT);
			
			//logout from everest,access eportal as multi user login,select
			//customer,go to usermanagement,click on add user
			logger.info("Step 4,5 :");
			logout();
			login("URLEportal","eportalusr_name","eportalpswd_name", multiUserLogin,userPswd,"newversnloginbttn_xpath",multiFirstName,multilastName);
			selUtils.clickOnWebElement(selUtils.getCommonObject("usermangement_link"));
			logger.info("Clicked on User management tab");
			selUtils.switchToFrame();
			selUtils.clickOnWebElement(selUtils.getObject("adduserbttn_xpath"));
			clkOnModRtsPlsBttn("expand_pmtrights_xpath","pmtrights_plsbttn_xpath");
			Assert.assertTrue(selUtils.getObject("detokenisationckbx_id").getAttribute("type").equals("checkbox"),"The Detokenization box is not displayed under the Payment rights section");
			logger.info("The Detokenization box is displayed under the Payment rights section");
			
			//logout from eportal as multi user,access eportal as multiuser
			//Go to the Customer module and select customer
			logger.info("Step 6,7:");
			logout();
			waitMethods.waitForWebElementPresent(selUtils.getCommonObject("eportalusr_name"));
			name=getCommonPath("eportalusr_name");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			login("URLEverest", "username_name", "password_name", multiUserLogin,userPswd, "submit_button_xpath",multiFirstName,multilastName);
			clkCustNameList(eportalCust);
			selUtils.verifyElementSelected(selUtils.getObject("uci_ckbx_id"));
            logger.info("The subscription to UCI checkbox is checked,verified");
            
            //Go to cardpayment,customer provisioning,add digest profile
            logger.info("Step 8:");
            navigateToSubPage(CUSTPROV,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("custprov_xpath"));
			selUtils.selectItem(selUtils.getCommonObject("cust_Sel_id"), testDataOR.get("customer"));
			selUtils.clickOnWebElement(selUtils.getObject("configtab_xpath"));
			selUtils.clickOnWebElement(selUtils.getCommonObject("plusbtn_xpath"));
			selUtils.selectItem(selUtils.getObject("notassignprof_id"), testDataOR.get("digest_profile").replaceAll(".zip", "").trim());
			selUtils.clickOnWebElement(selUtils.getObject("assignprofbttn_id"));
			selUtils.clickOnWebElement(selUtils.getObject("profokbttn_xpath"));
			vAddedProfile(appProfileName,"entitytablelst_css","configproflist_css");
			logger.info("Verified the Profile "+appProfileName+" is added in the profile list");
			
			
			//Click on provisioning tab,click on load digest key link
			logger.info("Step 9,10,11,12:");
			navigateToSubPage(CUSTPROV,selUtils.getCommonObject("cardpaymt_tab_xpath"),selUtils.getCommonObject("custprov_xpath"));
			selUtils.clickOnWebElement(selUtils.getObject("provtab_xpath"));
			selUtils.clickOnNavPaneItems(selUtils.getObject("cpplusbttn_xpath"),ATTRCLAS,VALCLOSE);
			selUtils.clickOnWebElement(selUtils.getObject("loaddigest_link"));
			selUtils.selectItem(selUtils.getObject("selprofdigest_id"), testDataOR.get("digest_profile").replaceAll(".zip", "").trim());
			waitMethods.waitForWebElementPresent(selUtils.getObject("digprofnext_id"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", selUtils.getObject("digprofnext_id"));
			selUtils.clickOnWebElement(selUtils.getObject("digmanualbttn_xpath"));
			selUtils.getObject("digprofcusnamefield_id").sendKeys(eportalCust);
			selUtils.getObject("digestkey_id").sendKeys(testDataOR.get("digest_key"));
			selUtils.getObject("tokenformat_id").sendKeys(tokenFormat);
			selUtils.clickOnWebElement(selUtils.getObject("loaddigestnextbtn_id"));
			
			//Enter a 'Project Name' project_name   click on the 'Save' button			
			logger.info("Step 13:");
			selUtils.clickOnWebElement(selUtils.getObject("viewconfignext_id"));
			logger.info("Clicked on next button");
			selUtils.populateInputBox("projname_id", projectName);
			logger.info("Entered the projectName as "+projectName);
			selUtils.clickOnWebElement(selUtils.getObject("configsumsavebttn_xpath"));
			waitNSec(3);
			
			//go to pending provisioning
			//Click the edit button of the pending operation,deploy project
			logger.info("Step 14:");
			clkOnPendingProv(projectName);
			clkOnDirectObj("editpospending_xpath","NAME",projectName);
			logger.info("Clicked on the edit button in Pending Provisioning Tab for the project");
			clickOnDeploy();
			selUtils.verifyTextContains(selUtils.getCommonObject("succ_deploymsg_xpath"), SUCSSFULLYDPLYD);
			logger.info("Verified the success message");
			
			//DB verification
			if(dbCheck){
				//Verify created estate name correctly updated in database
				sqlQuery="SELECT * FROM customerhmackey a INNER JOIN customer b ON a.customerid = b.id "
				+ "WHERE a.tr31 ='"+digestKey+"' AND b.tokenprofileid = '"+tokenFormat+"' AND b.customername = '"+eportalCust+"' ;";
				resSet = dbMethods.getDataBaseVal(dbTokenServer,sqlQuery,CommonConstants.ONEMIN);	
				logger.info("Verified the values of digest key and token format correctly inserted into the 'databaseTokenServer' database");
				
				//Retrieve row size
				if(resSet.getRow()==1)
					logger.info("Retrieved only one row sucessfully ");
					else
					Assert.fail(" Failed due to Row size is : "+resSet.getRow());
			}
			
			//go to the everest eportal module
			logger.info("Step 15:");
			selUtils.getCommonObject("eportal_tab_link").click();
			selUtils.selectItem(selUtils.getCommonObject("cust_Sel_id"), eportalCust);
			selUtils.slctChkBoxOrRadio(selUtils.getObject("token_availckbx_id"));
			selUtils.slctChkBoxOrRadio(selUtils.getObject("digest_availckbx_id"));
			selUtils.clickOnWebElement(selUtils.getCommonObject("savebttn_xpath"));
			Assert.assertTrue(selUtils.getCommonObject("msg_xpath").getText().equals("Customer "+eportalCust+" successfully updated"),eportalCust+" not updated successfully");
			logger.info("Verified message about the "+eportalCust+" successfully updated");
			
			//Logout multi user from ePortal
			//Logout is handeled in SSO
			logger.info("Step 16:");
			logger.info("Multi user logout is taken care at the suite level as part of the SSO");
			
			logger.info("SMR1269 executed successfully");

		}catch (Throwable t) {
			handleException(t);
		}

	}
	
}

