package com.StatusCheckerPages;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;

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

    public void checkEnabledCheckbox(Boolean action){
        interactWithCheckbox(enabledCheckboxLocator, action);
    }

    public void checkSelectDeselectCheckbox(Boolean action){interactWithCheckbox(selectDeselectCheckboxLocator, action);}

    public void checkForceDeletionCheckbox(Boolean action){interactWithCheckbox(forceDeletionDialogCheckboxLocator, action);}

    public void fillRequiredFields(String name, String abbreviation, String location,
                                   String plan, String apiMs, String xmppMs,
                                   String statistics, String adminLogin, String adminPass){
        clearAndType(nameFieldLocator, name);
        clearAndType(abbreviationFieldLocator, abbreviation);
        selectByValue(locationDropdownLocator, location);
        selectByValue(planDropdownLocator, plan);
        clearAndType(apiMsFieldLocator, apiMs);
        clearAndType(xmppMsFieldLocator, xmppMs);
        clearAndType(statisticsFieldLocator, statistics);
        clearAndType(adminLoginFieldLocator, adminLogin);
        clearAndType(adminPassFieldLocator, adminPass);
    }

    public void checkModulesCheckboxes(Boolean createSession, Boolean createUser, Boolean createUserSession,
                                      Boolean listUsers, Boolean updateUser, Boolean deleteUser, Boolean destroySession,
                                      Boolean createGeodata, Boolean listGeodata, Boolean createAndUploadContent,
                                      Boolean listContent, Boolean deleteContent, Boolean createData,
                                      Boolean updateData, Boolean listData, Boolean deleteData,
                                      Boolean createDialog, Boolean connectToChat, Boolean privateChat,
                                      Boolean privChatWithoutHis, Boolean privChatHl, Boolean groupChat,
                                      Boolean retrieveDialogs, Boolean removeDialogsOccup, Boolean addDialogOccup,
                                      Boolean createMessage, Boolean listMessages, Boolean deleteMessages,
                                      Boolean deleteDialog){
        interactWithCheckbox(createSessionCheckboxLocator, createSession);
        interactWithCheckbox(createUserCheckboxLocator, createUser);
        interactWithCheckbox(createUserSessionCheckboxLocator, createUserSession);
        interactWithCheckbox(listUsersCheckboxLocator, listUsers);
        interactWithCheckbox(updateUserCheckboxLocator, updateUser);
        interactWithCheckbox(deleteUserCheckboxLocator, deleteUser);
        interactWithCheckbox(destroySessionCheckboxLocator, destroySession);

        interactWithCheckbox(createGeodataCheckboxLocator, createGeodata);
        interactWithCheckbox(listGeodataCheckboxLocator, listGeodata);

        interactWithCheckbox(createAndUploadContentCheckboxLocator, createAndUploadContent);
        interactWithCheckbox(listContentCheckboxLocator, listContent);
        interactWithCheckbox(deleteContentCheckboxLocator, deleteContent);

        interactWithCheckbox(createDataCheckboxLocator, createData);
        interactWithCheckbox(updateDataCheckboxLocator, updateData);
        interactWithCheckbox(listDataCheckboxLocator, listData);
        interactWithCheckbox(deleteDataCheckboxLocator, deleteData);

        interactWithCheckbox(createDialogCheckboxLocator, createDialog);
        interactWithCheckbox(connectToChatCheckboxLocator, connectToChat);
        interactWithCheckbox(privateChatCheckboxLocator, privateChat);
        interactWithCheckbox(privateChatWithoutHistoryCheckboxLocator, privChatWithoutHis);
        interactWithCheckbox(privateChatHeadlineCheckboxLocator, privChatHl);
        interactWithCheckbox(groupChatCheckboxLocator, groupChat);
        interactWithCheckbox(retrieveDialogsCheckboxLocator, retrieveDialogs);
        interactWithCheckbox(removeDialogOccupantCheckboxLocator, removeDialogsOccup);
        interactWithCheckbox(addDialogOccupantCheckboxLocator, addDialogOccup);
        interactWithCheckbox(createMessageCheckboxLocator, createMessage);
        interactWithCheckbox(listMessagesCheckboxLocator, listMessages);
        interactWithCheckbox(deleteMessageCheckboxLocator, deleteMessages);
        interactWithCheckbox(deleteDialogCheckboxLocator, deleteDialog);
    }

    public void clickGenerateInstanceButton(){click(generateInstanceButtonLocator);}

}