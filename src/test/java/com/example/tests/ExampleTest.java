package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dgcode.wdaft.Tester;

public class ExampleTest extends Tester {
	
	 @BeforeClass
	 public void setUp() {
	   // code that will be invoked when this test is instantiated
	 }
	 
	 @Test(groups = { "fast" })
	 public void aFastTest() throws Exception {
	   System.out.println("Fast test");
	   WebDriver driver = getDriver();
	   
	   driver.get("http://www.google.com");
	 }
	 
	 @Test(groups = { "slow" })
	 public void aSlowTest() {
	    System.out.println("Slow test");
	 }


}
