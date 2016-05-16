package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ManageInstancesPage extends BasePage{


    public ManageInstancesPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/instances");
    }

    public int getNumberOfEnabledInstances(){
        return getCountOfCssElements(".enabled");
    }

    public int getNumberOfDisabledInstances(){
        return getCountOfCssElements(".disabled");
    }

    public int getNumberOfAllInstances(){
        return getCountOfCssElements(".name");
    }
}