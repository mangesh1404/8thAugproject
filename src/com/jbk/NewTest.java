package com.jbk;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class NewTest {
	
  @Test(dataProvider = "loginData")
  public void loginTest(String uname, String pass) {
	  
	  
  }

  @DataProvider
  public Object[][] loginData() {
    return new Object[][] {
      new Object[] { 1, "mangesh" },
      new Object[] { "", "b" },
    };
  }
}
