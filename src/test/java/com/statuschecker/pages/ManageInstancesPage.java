package com.statuschecker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ManageInstancesPage extends BasePage{

    private By devLocatorForActiveInstances = findByXPath("//a[descendant::*[@class='enabled']][descendant::*[@class='location'][contains(text(), 'us')]]");
    private By enabledInstancesLocator = findByCss(".enabled");
    private By disabledInstancesLocator = findByCss(".disabled");
    private By allInstancesLocator = findByCss(".name");
    private By addInstanceButtonLocator = findById("add-team-member");

    public ManageInstancesPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/instances");
    }

    public int getNumberOfEnabledInstances(){
        return getCountOfElements(enabledInstancesLocator);
    }

    public int getNumberOfDisabledInstances(){
        return getCountOfElements(disabledInstancesLocator);
    }

    public int getNumberOfAllInstances(){
        return getCountOfElements(allInstancesLocator);
    }

    public int getNumberOfDevInstances(){
        return getCountOfElements(devLocatorForActiveInstances);
    }

    public EditInstancePage openInstance(String instanceName){
        click(findByXPath("//a[@href='/admin/instances/" + instanceName + "']"));
        return new EditInstancePage(driver);
    }

}