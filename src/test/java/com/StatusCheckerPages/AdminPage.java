package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class AdminPage extends BasePage{

    public AdminPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin");
    }

}