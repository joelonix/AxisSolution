package com.ingenico.testsuite.gprs;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MethodOverRididngExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Animal{
	
	void method1() throws Exception
	{
	  System.out.println("this is method of animal");	
	}
	
}

class Lion extends Animal{
	
	void method1() throws Exception
	{
	  System.out.println("this is method of animal");	
	}
}
