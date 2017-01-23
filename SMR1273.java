package com.ingenico.testsuite.location;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/location/SMR1273.java $
$Id: SMR1273.java 16708 2016-01-20 09:53:29Z rjadhav $
 */
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * SMR-1273:Create e-Payment Location
 * @author Nagaveni.Guttula
 *
 */
public class SMR1273  extends SuiteLocation{
	//Declaration of class variables
	private final static String[] EPLOCPOPUPFIELDS={"locationname_id","workunit_id",
	"teamunit_id","webservice_url_id","eplogin_id","eppasswrd_id","epconfrmpaswrd_id"};

	@Test(groups="SMR1273")
	/**
	 * Create an e-Payment Location from Everest.
	 * @param browser
	 */
	public void smr1273() {

		try{
			final String firstName=testDataOR.get("superuser_first_name"),lastName=testDataOR.get("superuser_last_name");
			login("URLEverest",testDataOR.get("superuser"),firstName,lastName);
			logger.info("SMR1273 execution started");
			epayLocation=testDataOR.get("epayment_location");
			final String[] epLocPopUpVals={epayLocation,testDataOR.get("working_unit"),testDataOR.get("team_unit"),
			testDataOR.get("epayment_web_service_url"),testDataOR.get("epayment_login"),testDataOR.get("epayment_password"),testDataOR.get("epayment_password")};	

			//Access Everest with a superuser
			//Navigate to 'e-Payment Location' sub menu			
			logger.info("Step 1, 2:");
			navigateToSubPage(EPAYMNTLOCATION,selUtils.getCommonObject("epaymnt_tab_xpath"),selUtils.getCommonObject("epaymntlocation_xpath"));
			
			//Create e pay-location and verify it is successfully created 			
			logger.info("Step 3:");
			verifyExistingData("eplocation_delimg_xpath","colheaders_css","colallrows_xpath",epayLocation,NAMECOL);
			//clickOnWebElement(getObject("epaymntloc_plusicon_xpath"));
			selUtils.clickOnWebElement(selUtils.getCommonObject("plusbtn_xpath"));
			setValsForPopUpWin(selUtils.getCommonObject("popup_wintitle_id"),EPAYMENT_LOC,EPLOCPOPUPFIELDS,epLocPopUpVals);
			selUtils.clickOnWebElement(selUtils.getCommonObject("okbttn_xpath"));
			if(selUtils.getCommonObject("posheder_errmsg_id").getAttribute("class").endsWith("errorMessage"))
			{
				Assert.fail("Epayment location was not created due to error");	
				logger.info("Epayment location was not created due to error");
			}	
			logger.info("epayment location with name '"+epayLocation+"' successfully added");
			colIndex=selUtils.getIndexForColHeader("colheaders_css", NAMECOL);
			verifyLvlColLvlValPresence("entitytablelst_css",colIndex,epayLocation);		
			
			//Click the Test connection button
			logger.info("Step 4:");
		    clkOnDirectObj("testconn_bttn_xpath","NAME",epayLocation);
			xpath=getPath("validsign_xpath").replace("NAME", epayLocation);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Assert.assertTrue(selUtils.getObjectDirect(By.xpath(xpath)).isDisplayed(), "TestConnection icon is not changed to validated sign");
			logger.info("Test Connection icon is changed to validated sign");
			logger.info("SMR1273 executed successfully");	
		}
		catch (Throwable t) {
			handleException(t);
		}
	}
	
}