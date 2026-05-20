package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser target configured: " + browserName);

        // Dynamic Headless Toggle: Reads 'headless' from config.properties. Defaults to TRUE for pipeline safety.
        boolean headlessMode = Boolean.parseBoolean(prop.getProperty("headless", "true"));
        System.out.println("Running in Headless Mode: " + headlessMode);

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessMode)));
                break;
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessMode)));
                break;
            default:
                throw new IllegalArgumentException("Please pass a valid browser name. Received: " + browserName);
        }

        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }

    public Properties init_prop() {
        prop = new Properties();
        // FIXED: Using a bulletproof system path resolver so it maps seamlessly on local paths or CI workspace pools
        String configPath = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";

        try (FileInputStream ip = new FileInputStream(configPath)) {
            prop.load(ip);
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Unable to load config file at path: " + configPath);
            e.printStackTrace();
        }
        return prop;
    }
}