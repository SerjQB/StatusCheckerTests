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

    public ApplicationStatusPage(WebDriver driver){super(driver);}

    public void visit(String applicationName){
        open(currentDomain + "/instance/" + applicationName);
        waitUntilLoad();
    }

    public void clickOnRefreshButton(){
        click(refreshButtonLocator);
        waitUntilLoad();
    }

    public void openCalendar(){
        click(calendarButtonLocator);
    }

    public void clickOnExpandAllButton(){
        click(expandAllButtonLocator);
    }

    public void clickOnFullScreenButton(){
        click(fullScreenButtonLocator);
    }



}
