package com.qa.opencart.Pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String email = "//input[@id='input-email']";
    private String pwd   = "//input[@id='input-password']";
    private String loginbtn = "//input[@value='Login']";
    private String forgottenpwd = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private String logout = "//a[@class='list-group-item'][normalize-space()='Logout']";
    //page constructor
    public LoginPage(Page page){
        this.page=page;
    }
    //page actions/methods
    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isForgottenPwdExist(){
        return page.isVisible(forgottenpwd);
    }

    public boolean doLogin(String appusername,String appuserpwd){
        System.out.println("App cred" +appusername +":"+appuserpwd);
        page.fill(email,appusername);
        page.fill(pwd,appuserpwd);
        page.click(loginbtn);
        if (page.isVisible(logout)){
            System.out.println("user successully logged in ....");
            return true;
        }
           return false;
    }
}
