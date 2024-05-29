package com.restAssured.RestAssuredDemo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestnGTest2 {
  @Test
  public void test() 
  {
	  System.out.println("test method");
  }
  @BeforeTest
  public void beforeTest()
  {
	  System.out.println("before test");
  }

  @AfterTest
  public void afterTest() 
  {
	  System.out.println("after test");
  }
}
