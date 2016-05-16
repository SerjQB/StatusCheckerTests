package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class AddInstancePage extends BasePage{

    public AddInstancePage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/instances/add");
    }

}