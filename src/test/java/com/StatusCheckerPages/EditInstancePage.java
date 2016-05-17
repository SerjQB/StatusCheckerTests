package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class EditInstancePage extends ManageInstancesPage{

    private By latestCheckLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By checkCountsLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By daysRunningLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By modulesEnabledLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By saveInstanceButtonLocator = findById("save_instance");
    private By deleteInstanceButtonLocator = findById("delete_instance");

    public EditInstancePage(WebDriver driver){super(driver);}

    public void visit(String instanceName){
        open(currentDomain + "/admin/instances/" + instanceName);
    }

    public String getLatestCheckNumber(){return getText(latestCheckLocator).replaceAll(" ", "");}

    public String getCheckCountsNumber(){return getText(checkCountsLocator).replaceAll(" ", "");}

    public String getDaysRunningNumber(){return getText(daysRunningLocator).replaceAll(" ", "");}

    public String getModulesEnabledNumber(){return getText(modulesEnabledLocator).replaceAll(" ", "");}

    public void clickSaveInstanceButton(){click(saveInstanceButtonLocator);}

    public void clickDeleteInstanceButton(){click(deleteInstanceButtonLocator);}
}


