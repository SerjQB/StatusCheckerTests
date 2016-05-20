package com.StatusCheckerPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class ApplicationStatusPage extends BasePage {

    protected By titleFieldLocator = findByCss(".page-title strong");
    protected By refreshButtonLocator = findById("refresh");
    protected By expandAllButtonLocator = findById("toggle-graphs");
    protected By fullScreenButtonLocator = findById("toggle-width");
    protected By calendarButtonLocator = findById("datepicker");
    protected By createUserLocator = findById("createUser");
    protected By createUserSessionLocator = findById("createUserSession");
    protected By listUsersLocator = findById("listUsers");
    protected By updateUserLocator = findById("updateUser");
    protected By deleteUserLocator = findById("deleteUser");
    protected By destroySessionLocator = findById("destroySession");
    protected By createGeodataLocator = findById("createGeodata");
    protected By listGeodataLocator = findById("listGeodata");
    protected By createAndUploadContentLocator = findById("createAndUploadContent");
    protected By listContentLocator = findById("listContent");
    protected By deleteContentLocator = findById("deleteContent");
    protected By createDataLocator = findById("createData");
    protected By updateDataLocator = findById("updateData");
    protected By listDataLocator = findById("listData");
    protected By deleteDataLocator = findById("deleteData");
    protected By createDialogLocator = findById("createDialog");
    protected By connectToChatLocator = findById("connectToChat");
    protected By privateChatLocator = findById("privateChat");
    protected By privateChatWithoutHistoryLocator = findById("privateChatWithoutHistory");
    protected By privateChatWithoutHeadlineLocator = findById("privateChatWithoutHeadline");
    protected By groupChatLocator = findById("groupChat");
    protected By retrieveDialogsLocator = findById("retrieveDialogs");
    protected By removeDialogOccupantLocator = findById("removeDialogOccupant");
    protected By addDialogOccupantLocator = findById("addDialogOccupant");
    protected By createMessageLocator = findById("createMessage");
    protected By listMessagesLocator = findById("listMessages");
    protected By deleteMessageLocator = findById("deleteMessage");
    protected By deleteDialogLocator = findById("deleteDialog");
    protected By pushAndroidLocator = findById("android");
    protected By pushiosLocator = findById("ios");
    protected By healthCheckLocator = findByCss("#healthcheck .time");
    protected By metricsCounterLocator = findByXPath("//span[@class='title'][not(text()='Healthcheck' and not(contains(text(), 'Push')))][ancestor::*[@data-obj]]");
    protected By expandedGraphsIconLocator = findByXPath("//*[@class='angle icon up']");
    protected By hiddenGraphsIconLocator = findByXPath("//*[@class='angle icon down']");
    protected By expandedScreenLocator = findByXPath("//*[@class='icon compress']");
    protected By compressedScreenIconLocator = findByXPath("//*[@class='expand icon']");
    protected By contentLocator = findById("content");
    protected By buildedGraphsCounterLocator = findByCss("div[class='row stat graph_wrap opened']");
    protected By openedGraphsLocator = findByCss("div[class='row stat graph_wrap graph_builded opened']");
    protected By openedWithExpandGraphsLocator = findByCss("div[class='row stat graph_wrap opened graph_builded']");
    protected By allMetricsCounterLocator = findByCss("span.title");
    protected By hiddenGraphsCounterLocator = findByCss("div[class='row stat graph_wrap graph_builded']");


    public ApplicationStatusPage(WebDriver driver) {
        super(driver);
    }

    public void visit(String applicationName) {
        open(currentDomain + "/instance/" + applicationName);
        waitUntilLoad();
        waitUntilElement(healthCheckLocator);
    }

    public String getValueOfMetric(String metricName){return getText(findByCss("#" + metricName + " .time "));}

    public void clickMetricIndex(int index){
        click(findByXPath(getIndexElementXpath(index)));
    }

    public Boolean isIndexGraphPresented(int index){
        waitUntilElementNotPresented(findByCss("div[class='row stat graph_wrap'][" +
                Integer.toString(index) + "]"));
        return isElementPresented(findByXPath(getIndexElementXpath(index) + "//canvas"));
    }

    public Boolean isIndexGraphHidden(int index){
        pause(100);
        return isElementPresented(findByXPath("//div[@class='row stat graph_wrap graph_builded'][" + index + "]"));
    }

    public String getHealthCheckValue(){return getText(healthCheckLocator);}

    public int getCountOfMetrics(){return getCountOfElements(metricsCounterLocator);}

    public int getCountOfAllMetrics(){return getCountOfElements(allMetricsCounterLocator);}

    public int getCountOfExpandedGraphs(){return getCountOfElements(openedGraphsLocator);}

    public int getCountOfFullScreenExpandedGraphs(){return getCountOfElements(openedWithExpandGraphsLocator);}

    public int getCountOfHiddenGraphs(){return getCountOfElements(hiddenGraphsCounterLocator);}

    public void clickOnRefreshButton() {
        click(refreshButtonLocator);
        waitUntilLoad();
    }

    public double getContentWidth(){return getWidth(contentLocator);}

    public void openCalendar() {click(calendarButtonLocator);}

    public void clickOnExpandAllButton() {click(expandAllButtonLocator);}

    public void clickOnFullScreenButton() {click(fullScreenButtonLocator);}

    public void waitForFullScreen(){waitUntilElementNotPresented(compressedScreenIconLocator);}

    public void waitForCompressedScreen(){waitUntilElementNotPresented(expandedScreenLocator);}

    public void waitForExpandedGraphs(){waitUntilElementNotPresented(hiddenGraphsIconLocator);}

    public void waitForHiddenGraphs(){waitUntilElementNotPresented(expandedGraphsIconLocator);}

    public void waitForExpandedChangeScreen(){waitUntilElementNotPresented(buildedGraphsCounterLocator);}

    private String getIndexElementXpath(int index){
        return ("//*[@data-obj][" + Integer.toString(index) + "]");
    }
}
