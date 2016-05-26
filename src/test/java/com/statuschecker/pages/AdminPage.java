package com.statuschecker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class AdminPage extends BasePage{

    private By addInstancesButtonLocator = findByXPath("//a[@href='/admin/instances/add']");
    private By manageInstancesButtonLocator = findByXPath("//a[@href='/admin/instances/add']");
    private By addTeamsButtonLocator = findByXPath("//a[@href='/admin/teams#add']");
    private By manageTeamButtonLocator = findByXPath("//a[@href='/admin/teams']");

    public AdminPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin");
    }

}