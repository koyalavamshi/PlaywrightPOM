package com.qa.opencart.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;
import com.qa.opencart.Pages.HomePage;
import com.qa.opencart.Pages.LoginPage;
import com.qa.opencart.factory.PlaywrightFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Playwright playwright;
    Browser browser;
    Page page;
    
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();

        // Total Override: Initialize Playwright directly here to guarantee headless execution
        playwright = Playwright.create();
        
        // This line forces Chromium to launch headlessly, ignoring the factory's launch settings
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        
        page = browser.newPage();
        
        // Pass your direct headless page straight to your Page Objects
        homePage = new HomePage(page);
    }

    @AfterMethod
    public void teardown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
