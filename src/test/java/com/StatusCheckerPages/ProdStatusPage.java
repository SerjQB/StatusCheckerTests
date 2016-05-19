package com.StatusCheckerPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ProdStatusPage extends ApplicationStatusPage{

    private String CPUUsage = "#chatc";
    private String MessagesNumberPerSecond = "#chatm";
    private String PresencesNumberPerSecond = "#chatp";
    private String QueueSize = "#chatq";
    private String ConnectionsNumber = "#chatu";

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
    }


}
