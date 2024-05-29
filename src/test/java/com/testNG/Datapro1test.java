
package com.testNG;
 
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
 
public class Datapro1test {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
 
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "Kunal", "consultant" },
      new Object[] { 2, "b" },
    };
  }
}