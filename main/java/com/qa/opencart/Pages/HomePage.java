package com.qa.opencart.Pages;

import com.microsoft.playwright.Page;

public class HomePage {
    Page page;
    //String locators --OR
    private String Search = "input[name='search']";
    private String SearchIcon = "div#search button";
    private String SearchPageHeader = "div#content h1";
    private String loginLink = "a:text('Login')";
    private String MyAccount = "a[title='My Account']";
    //page constructor
    public HomePage(Page page){
        this.page=page;

    }

    //page actions/methods
    public String getHomePageTitle(){
       String title = page.title();
       System.out.println("title of homepage:"+title);
       return title;
    }

    public String getHomePageUrl(){
        String url = page.url();
        System.out.println("url of page"+ url);
        return url;
    }


    public String doSearch(String productName){
        page.fill(Search,productName);
        page.click(SearchIcon);
        String header = page.textContent(SearchPageHeader);
        System.out.println("Search header"+ header);
        return header;
    }

    public LoginPage navigateToLoginPage(){
        page.click(MyAccount);
        page.click(loginLink);
        return new LoginPage(page);
    }



}
