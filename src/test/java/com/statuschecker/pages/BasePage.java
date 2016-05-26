package com.statuschecker.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.statuschecker.initializations.Wrappers;
import com.statuschecker.test.BaseTest;


public abstract class BasePage extends Wrappers {

    protected String openedBrowser = BaseTest.getCurrentBrowser();

    public static String currentDomain = BaseTest.getCurrentDomain();

    private By logoLoadLocator = findByCss("qbLogo.loading");
    private By logoLocator = findById("qbLogo");

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
        waitUntilElement(logoLocator);
        waitUntilElementNotPresented(logoLoadLocator);
    }

    public AddInstancePage openAddInstancePage(){
        open(currentDomain + "/admin/instances/add");
        return new AddInstancePage(driver);
    }

    public AdminPage openAdminPage(){
        open(currentDomain + "/admin");
        return new AdminPage(driver);
    }

    public ApplicationStatusPage openApplicationStatusPage(String instanceName){
        open(currentDomain + "/instance/" + instanceName);
        return new ApplicationStatusPage(driver);
    }

    public DisplayPage openDisplayPage(){
        open(currentDomain + "/admin/display");
        return new DisplayPage(driver);
    }

    public EditInstancePage openEditInstancePage(String instanceName){
        open(currentDomain + "/admin/instances/"+ instanceName);
        return new EditInstancePage(driver);
    }

    public ManageInstancesPage openManageInstancesPage(){
        open(currentDomain + "/admin/instances");
        return new ManageInstancesPage(driver);
    }

    public ProdStatusPage openProdStatusPage(){
        open(currentDomain);
        return new ProdStatusPage(driver);
    }

    public TeamsPage openTeamsPage(){
        open(currentDomain + "/admin/teams");
        return new TeamsPage(driver);
    }

}
