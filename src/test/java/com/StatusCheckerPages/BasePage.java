package com.StatusCheckerPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.TestInitializations.Wrappers;
import com.StatusCheckerTests.MainTest;


public class BasePage extends Wrappers {

    protected String openedBrowser = MainTest.getCurrentBrowser();

    private By logoLoadLocator = findByCss("qbLogo.loading");

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public void waitUntilLoad(){
        waitUntilElementNotPresented(logoLoadLocator);
    }

    public AddInstancePage openAddInstancePage(){
        open("https://status.quickblox.com/admin/instances/add");
        return  new AddInstancePage(driver);
    }

    public AdminPage openAdminPage(){
        open("https://status.quickblox.com/admin");
        return  new AdminPage(driver);
    }

    public ApplicationStatusPage openApplicationStatusPage(String instanceName){
        open("https://status.quickblox.com/instance/" + instanceName);
        return  new ApplicationStatusPage(driver);
    }

    public DisplayPage openDisplayPage(){
        open("https://status.quickblox.com/admin/display");
        return  new DisplayPage(driver);
    }

    public EditInstancePage openEditInstancePage(String instanceName){
        open("https://status.quickblox.com/admin/instances/"+ instanceName);
        return  new EditInstancePage(driver);
    }

    public ManageInstancesPage openManageInstancesPage(){
        open("https://status.quickblox.com/admin/instances");
        return  new ManageInstancesPage(driver);
    }

    public ProdStatusPage openProdStatusPage(){
        open("https://status.quickblox.com");
        return  new ProdStatusPage(driver);
    }

    public TeamsPage openTeamsPage(){
        open("https://status.quickblox.com/admin/teams");
        return  new TeamsPage(driver);
    }

}
