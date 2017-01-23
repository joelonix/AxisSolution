package com.ingenico.testsuite.assettracking;
/*
$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/testsuite/assettracking/SuiteAssetTracking.java $
$Id: SuiteAssetTracking.java 16128 2015-12-09 10:38:34Z haripraks $
 */


import org.testng.annotations.BeforeSuite;

import com.ingenico.base.TestBase;
/**
 * Common Methods and Variables for the asset tracking package
 *
 */
public class SuiteAssetTracking extends TestBase {

	//TODO***Framework Related Functions***
	
	/** 
	 * Initializes suite execution
	 */
	@BeforeSuite(alwaysRun=true)
	void initSetUp()  {
		initialize();
	}	
	
	//TODO****Common functions to Asset Tracking module****

}
