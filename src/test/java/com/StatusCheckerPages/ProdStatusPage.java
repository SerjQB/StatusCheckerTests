package com.StatusCheckerPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ProdStatusPage extends ApplicationStatusPage{

    private By CPUUsageLocator = findById("chatc");
    private By MessagesNumberPerSecondLocator = findById("chatm");
    private By PresencesNumberPerSecondLocator = findById("chatp");
    private By QueueSizeLocator = findById("chatq");
    private By ConnectionsNumberLocator = findById("chatu");


    public ProdStatusPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        open(currentDomain + "/instance/prod");
        waitUntilLoad();
        waitUntilElement(CPUUsageLocator);
    }

    public String getAdminStat(String statName){
        return getText(findByXPath("//span[text()='"
                + statName + "']/ancestor::*[@data-obj]//span[@class = 'time pass']"));
    }

}
