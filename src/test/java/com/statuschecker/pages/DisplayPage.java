package com.statuschecker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class DisplayPage extends BasePage{


    private By graphElementLocator = findByCss("div[class~='complete']");
    private By counterLocator = findById("counter");

    public DisplayPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/display");
    }

    public void waitForAllInstances(int numberOfInstances){
        waitUntilElementVisible(graphElementLocator);
        while (getCountOfCssElements("div[class~='complete']") < numberOfInstances){
            pause(500);
        }
    }

    public int getNumberInCounter(){return Integer.parseInt(getText(counterLocator));}

    public int getNumberOfGraphs() {
        return getCountOfCssElements("div[class~='complete']");}

}
