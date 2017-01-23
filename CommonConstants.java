package com.ingenico.common;

import java.io.File;

/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/E2E/trunk/src/com/ingenico/common/CommonConstants.java $
	$Id: CommonConstants.java 17309 2016-02-29 09:22:01Z haripraks $
*/
	
/**
 * Declares the Generic  variables 
 * 
 */

public class CommonConstants {
	
	public final static String USERDIR="user.dir";
	public final static String DATAFOLDER="data";
	public final static String TESTDATA = "testdata";
	public final static String CONFIGFILE = "config"+File.separator+"config.xml";
	public final static String SCREENSHOTPATH = System.getProperty(USERDIR)+File.separator+"screenshots"+File.separator+"screenshots_";
	public final static String SCREENSHOTLINK = "http://cosuprjnk:8080/job/projectPath/ws/screenshots/screenshots_";
	public final static String IEDRIVERPATH = System.getProperty(USERDIR)+File.separator+"external"+File.separator+"selenium"+File.separator;
	public final static String DATA = System.getProperty(USERDIR)+File.separator+DATAFOLDER+File.separator;
	public final static String C3PO = System.getProperty(USERDIR)+File.separator+"C3PO"+File.separator;
	public final static String COMMONCONFIGFILE = "object-locator"+File.separator+"common_UIMap.xml";
	
	public final static int ONESEC = 1000;
	public final static int TWOSEC = 2000;
	public final static int THREESEC = 3000;
	public final static int FOURSEC = 4000;
	public final static int FIVESEC = 5000;
	public final static int SIXSEC = 6000;
	public final static int SEVENSEC = 7000;
	public final static int TIMEOUTSIXTYSEC = 60000;
	public final static int ONEMIN = 60;
	public final static int TWOMIN = 120;
	public final static int IMPLICITTIME=5;
	
	public final static String INPUTYAMLFILE=DATAFOLDER+File.separator+TESTDATA+File.separator+"testdata.yml";
	public final static String TESTDATAFILEPATH = System.getProperty(USERDIR)+File.separator+DATAFOLDER+File.separator+TESTDATA+File.separator+"testdata_rythm"+File.separator+"testdata_rythm_";
	public static String fileDownloadPath = System.getProperty(USERDIR)+File.separator+DATAFOLDER+File.separator+"file_downloads"+File.separator;
	public static String fileUploadpath = System.getProperty(USERDIR)+File.separator+DATAFOLDER+File.separator+"file_uploads"+File.separator;
	public final static String OBJECTLOC=System.getProperty(USERDIR)+File.separator+"object-locator"+File.separator;
	public final static String POSCSV = fileUploadpath+"pos.csv";
	public final static String NUMCOMMCSV = fileUploadpath+"numcomm.csv";
	public final static String POSTONUMCOMMCSV = fileUploadpath+"postonumcomm.csv";
	public final static String POSNUMCOMMCSV = fileUploadpath+"posnumcomm.csv";
	public final static String IMPSIMCSV=fileUploadpath +"import_sim.csv";
	public final static String ACTCONFIRMCSV=fileUploadpath +"activationConfirmation.csv";
	public final static String ACTREQCSV=fileUploadpath +"activationRequest.csv";
	
	
	public final static String INPUTFILEPATH=DATAFOLDER+File.separator+TESTDATA+File.separator;
	public final static String TRUSTSTOREPATH=System.getProperty(USERDIR)+File.separator+"config"+File.separator+"trustStore"+File.separator+"id_rsa_autotest";
}
