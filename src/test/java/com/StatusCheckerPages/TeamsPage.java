package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class TeamsPage extends BasePage{

    public TeamsPage(WebDriver driver){super(driver);}

    public void visit(){
        open("https://status.quickblox.com/admin/teams");
    }

}
