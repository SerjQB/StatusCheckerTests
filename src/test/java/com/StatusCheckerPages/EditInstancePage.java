package com.StatusCheckerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class EditInstancePage extends AddInstancePage{

    private By latestCheckLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By checkCountsLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By daysRunningLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By modulesEnabledLocator = findByXPath("//*[@class='label'][contains(text(), 'Latest check')]/preceding-sibling::div");
    private By saveInstanceButtonLocator = findById("save_instance");
    private By deleteInstanceButtonLocator = findById("delete_instance");

    private By checkedMetricsCounter = findByXPath(".//input[contains(@id, 'modules')][@checked]");

    public EditInstancePage(WebDriver driver){super(driver);}

    public void visit(String instanceName){
        open(currentDomain + "/admin/instances/" + instanceName);
    }

    public String getLatestCheckNumber(){return getText(latestCheckLocator).replaceAll(" ", "");}

    public String getCheckCountsNumber(){return getText(checkCountsLocator).replaceAll(" ", "");}

    public String getDaysRunningNumber(){return getText(daysRunningLocator).replaceAll(" ", "");}

    public String getModulesEnabledNumber(){return getText(modulesEnabledLocator).replaceAll(" ", "");}

    public void clickSaveInstanceButton(){click(saveInstanceButtonLocator);}

    public void clickDeleteInstanceButton(){click(deleteInstanceButtonLocator);}

    public String[] getMetricsNameArray(){
        String[] arr = new String[getCountOfElements(checkedMetricsCounter)];
        int counter = 0;

        if(isChecked(createSessionCheckboxLocator)){
            arr[counter] = "createSession";
            counter++;
        }

        if(isChecked(createUserCheckboxLocator)){
            arr[counter] = "createUser";
            counter++;
        }

        if(isChecked(createUserSessionCheckboxLocator)){
            arr[counter] = "createUserSession";
            counter++;
        }

        if(isChecked(listUsersCheckboxLocator)){
            arr[counter] = "listUsers";
            counter++;
        }

        if(isChecked(updateUserCheckboxLocator)){
            arr[counter] = "updateUser";
            counter++;
        }

        if(isChecked(deleteUserCheckboxLocator)){
            arr[counter] = "deleteUser";
            counter++;
        }

        if(isChecked(destroySessionCheckboxLocator)){
            arr[counter] = "destroySession";
            counter++;
        }

        if(isChecked(createGeodataCheckboxLocator)){
            arr[counter] = "createGeodata";
            counter++;
        }

        if(isChecked(listGeodataCheckboxLocator)){
            arr[counter] = "listGeodata";
            counter++;
        }

        if(isChecked(createAndUploadContentCheckboxLocator)){
            arr[counter] = "createAndUploadContent";
            counter++;
        }

        if(isChecked(listContentCheckboxLocator)){
            arr[counter] = "listContent";
            counter++;
        }

        if(isChecked(deleteContentCheckboxLocator)){
            arr[counter] = "deleteContent";
            counter++;
        }

        if(isChecked(createDataCheckboxLocator)){
            arr[counter] = "createData";
            counter++;
        }

        if(isChecked(updateDataCheckboxLocator)){
            arr[counter] = "updateData";
            counter++;
        }

        if(isChecked(listDataCheckboxLocator)){
            arr[counter] = "listData";
            counter++;
        }

        if(isChecked(deleteDataCheckboxLocator)){
            arr[counter] = "deleteData";
            counter++;
        }

        if(isChecked(createDialogCheckboxLocator)){
            arr[counter] = "createDialog";
            counter++;
        }

        if(isChecked(connectToChatCheckboxLocator)){
            arr[counter] = "connectToChat";
            counter++;
        }

        if(isChecked(privateChatCheckboxLocator)){
            arr[counter] = "privateChat";
            counter++;
        }

        if(isChecked(privateChatWithoutHistoryCheckboxLocator)){
            arr[counter] = "privateChatWithoutHistory";
            counter++;
        }

        if(isChecked(privateChatHeadlineCheckboxLocator)){
            arr[counter] = "privateChatHeadlineC";
            counter++;
        }

        if(isChecked(groupChatCheckboxLocator)){
            arr[counter] = "groupChat";
            counter++;
        }

        if(isChecked(retrieveDialogsCheckboxLocator)){
            arr[counter] = "retrieveDialogs";
            counter++;
        }

        if(isChecked(removeDialogOccupantCheckboxLocator)){
            arr[counter] = "removeDialogOccupant";
            counter++;
        }

        if(isChecked(addDialogOccupantCheckboxLocator)){
            arr[counter] = "addDialogOccupant";
            counter++;
        }

        if(isChecked(createMessageCheckboxLocator)){
            arr[counter] = "createMessage";
            counter++;
        }

        if(isChecked(listMessagesCheckboxLocator)){
            arr[counter] = "listMessages";
            counter++;
        }

        if(isChecked(deleteMessageCheckboxLocator)){
            arr[counter] = "deleteMessage";
            counter++;
        }

        if(isChecked(deleteDialogCheckboxLocator)){
            arr[counter] = "deleteDialog";
            counter++;
        }

        return arr;
    }

}


