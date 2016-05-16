package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class EditInstancePage extends BasePage{

    public EditInstancePage(WebDriver driver){super(driver);}

    public void visit(String instanceName){
        open(currentDomain + "/admin/instances/" + instanceName);
    }

}


