package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {


    @BeforeMethod
    public  void loginPageSetup(){
        loginPage= homePage.navigateToLoginPage();
    }

    @Test(priority=1)
    public void loginPageTest(){
       String actualPageTiltle =loginPage.getLoginPageTitle();
        Assert.assertEquals(actualPageTiltle, AppConstants.LOGIN_PAGE_TITLE);
    }
    @Test(priority = 2)
    public void forgotPwdLinkExistTest(){
        Assert.assertTrue(loginPage.isForgottenPwdExist());
    }

    @Test(priority = 3)
    public void appLoginTest(){
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim()));
    }
}
