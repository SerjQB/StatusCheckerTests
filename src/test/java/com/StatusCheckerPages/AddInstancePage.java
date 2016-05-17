package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class AddInstancePage extends BasePage{

    protected By nameFieldLocator = findById("name");
    protected By enabledCheckboxLocator = findById("enabled");
    protected By abbreviationFieldLocator = findById("abbr");
    protected By locationDropdownLocator = findById("location");
    protected By planDropdownLocator = findById("plan");
    protected By apiMsFieldLocator = findById("timeout-api");
    protected By xmppMsFieldLocator = findById("timeout-chat");
    protected By statisticsFieldLocator = findById("chat-statistics");
    protected By adminLoginFieldLocator = findById("admin-login");
    protected By adminPassFieldLocator = findById("admin-password");
    protected By generateInstanceButtonLocator = findById("generate_params");
    protected By selectDeselectCheckboxLocator = findById("select_all");
    protected By forceDeletionDialogCheckboxLocator = findById("instance_force");


    protected By createSessionCheckboxLocator = findById("modules-session_module-createSession");
    protected By createUserCheckboxLocator = findById("modules-session_module-createUser");
    protected By createUserSessionCheckboxLocator = findById("modules-session_module-createUserSession");
    protected By listUsersCheckboxLocator = findById("modules-session_module-listUsers");
    protected By updateUserCheckboxLocator = findById("modules-session_module-updateUser");
    protected By deleteUserCheckboxLocator = findById("modules-session_module-deleteUser");
    protected By destroySessionCheckboxLocator = findById("modules-session_module-destroySession");

    protected By createGeodataCheckboxLocator = findById("modules-geodata_module-createGeodata");
    protected By listGeodataCheckboxLocator = findById("modules-geodata_module-listGeodata");

    protected By createAndUploadContentCheckboxLocator = findById("modules-content_module-createAndUploadContent");
    protected By listContentCheckboxLocator = findById("modules-content_module-listContent");
    protected By deleteContentCheckboxLocator = findById("modules-content_module-deleteContent");

    protected By createDataCheckboxLocator = findById("modules-data_module-createData");
    protected By updateDataCheckboxLocator = findById("modules-data_module-updateData");
    protected By listDataCheckboxLocator = findById("modules-data_module-listData");
    protected By deleteDataCheckboxLocator = findById("modules-data_module-deleteData");

    protected By createDialogCheckboxLocator = findById("modules-dialog_module-createDialog");
    protected By connectToChatCheckboxLocator = findById("modules-dialog_module-connectToChat");
    protected By privateChatCheckboxLocator = findById("modules-dialog_module-privateChat");
    protected By privateChatWithoutHistoryCheckboxLocator = findById("modules-dialog_module-privateChatWithoutHistory");
    protected By privateChatHeadlineCheckboxLocator = findById("modules-dialog_module-privateChatHeadline");
    protected By groupChatCheckboxLocator = findById("modules-dialog_module-groupChat");
    protected By retrieveDialogsCheckboxLocator = findById("modules-dialog_module-retrieveDialogs");
    protected By removeDialogOccupantCheckboxLocator = findById("modules-dialog_module-removeDialogOccupant");
    protected By addDialogOccupantCheckboxLocator = findById("modules-dialog_module-addDialogOccupant");
    protected By createMessageCheckboxLocator = findById("modules-dialog_module-createMessage");
    protected By listMessagesCheckboxLocator = findById("modules-dialog_module-listMessages");
    protected By deleteMessageCheckboxLocator = findById("modules-dialog_module-deleteMessage");
    protected By deleteDialogCheckboxLocator = findById("modules-dialog_module-deleteDialog");

    public AddInstancePage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/instances/add");
    }

}