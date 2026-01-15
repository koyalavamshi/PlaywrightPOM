package com.qa.opencart.tests;


import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void gethomepagetitle(){
        String actualtitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualtitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void gethomepageurl(){
       String actualurl = homePage.getHomePageUrl();
       Assert.assertEquals(actualurl,prop.getProperty("url"));
    }
    @DataProvider
    public Object[][] getproductData(){
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }
    @Test(dataProvider = "getproductData")
    public void searchTest(String ProductName){
        String actualheader=homePage.doSearch(ProductName);
        Assert.assertEquals(actualheader,"Search - "+ProductName);
    }


}
