package com.ingenico.testsuite.tokenserver;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/cardpayment/SMR140.java $
$Id: SMR1319.java 14709 2015-08-28 12:34:30Z rkahreddyga $
 */
import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;

/**
 * Common methods and variables of Suite Token Server
 *
 */

public class SuiteTokenServer extends TestBase {

	public final static String PROFILEZIP="profiles.zip",EDITPROFILE="Edit profile assignment";
	
	//TODO******Framework Related Functions*****************

	/** 
	 * Initializes suite execution
	 */
	@BeforeSuite(alwaysRun=true)
	void initSetUp()  {
		initialize();
	}
	
	
}