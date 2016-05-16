package com.StatusCheckerPages;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class TeamsPage extends BasePage{

    private By addTeamMemberButtonLocator = findById("add-team-member");
    private By nameFieldLocator = findById("team_member_name");
    private By phoneFieldLocator = findById("team_member_phone");
    private By mailFieldLocator = findById("team_member_email");
    private By saveTeamMemberButtonLocator = findById("save_team_member");
    private By successMessageLocator = findByXPath("//p[text()='All is ok']");
    private By closeButtonLocator = findByCss("#user-message button");
    private By messageTextLocator = findByCss("#user-message h3");
    private By nameErrorLocator = findByXPath("//*[@class = 'field error']/input[@id='team_member_name']//following-sibling::div");
    private By emailErrorLocator = findByXPath("//*[@class = 'field error']/input[@id='team_member_email']//following-sibling::div");
    private By phoneErrorLocator = findByXPath("//*[@class = 'field error']/input[@id='team_member_phone']//following-sibling::div");

    public TeamsPage(WebDriver driver){super(driver);}

    public void visit(){
        open(currentDomain + "/admin/teams");
    }

    private String getXpathToTheNeedfulTeamMember(String name, String phone, String mail){
        return "//*[child::*[@class='name'][contains(text(), '" + name +
                "')]][child::*[contains(@class, 'phone')][contains(text(),'" + phone
                + "')]][child::*[contains(@class, 'email')][contains(text(),'" + mail + "')]]";
    }

    private String getXpathToTheNeedfulTeamMember(String name, String mail){
        return "//*[child::*[@class='name'][contains(text(), '" + name
                + "')]][child::*[contains(@class, 'email')][contains(text(),'" + mail + "')]]";
    }

    public Boolean isCreatedTeamMemberExist(String name, String phone, String mail){
        return isElementPresented(findByXPath(getXpathToTheNeedfulTeamMember(name, phone, mail)));
    }

    public Boolean isCreatedTeamMemberExist(String name, String mail){
        return isElementPresented(findByXPath(getXpathToTheNeedfulTeamMember(name, mail)));
    }

    public void clickSaveTeamMemberButton(){click(saveTeamMemberButtonLocator);}

    public void clickAddTeamMemberButton(){click(addTeamMemberButtonLocator);}

    public void fillRequiredFields(String name, String mail){
        clearAndType(nameFieldLocator, name);
        clearAndType(mailFieldLocator, mail);
    }

    public void fillTheFields(String name, String phone, String mail){
        clearAndType(nameFieldLocator, name);
        clearAndType(phoneFieldLocator, phone);
        clearAndType(mailFieldLocator, mail);
    }

    public void addNewTeamMember(String name, String phone, String mail){
        clickAddTeamMemberButton();
        fillTheFields(name, phone, mail);
        clickSaveTeamMemberButton();
    }

    public void addNewTeamMember(String name, String mail){
        clickAddTeamMemberButton();
        fillRequiredFields(name, mail);
        clickSaveTeamMemberButton();
    }

    public Boolean isSuccessMessagePresented(){return isElementPresented(successMessageLocator);}

    public void closeMessage(){
        waitUntilElementVisible(closeButtonLocator);
        click(closeButtonLocator);
        waitUntilElementNotPresented(closeButtonLocator);
    }

    public void openEditMemberForm(String name, String phone, String mail){
        click(findByXPath(getXpathToTheNeedfulTeamMember(name, phone, mail) + "//button[@data-edit-team]"));
    }

    public void openEditMemberForm(String name, String mail){
        click(findByXPath(getXpathToTheNeedfulTeamMember(name, mail) + "//button[@data-edit-team]"));
    }

    public void clickDeleteUserButton(String name, String phone, String mail){
        //waitUntilElement(findByXPath(getXpathToTheNeedfulTeamMember(name, phone, mail) + "//button[@data-delete-team]"));
        click(findByXPath(getXpathToTheNeedfulTeamMember(name, phone, mail) + "//button[@data-delete-team]"));
    }

    public void clickDeleteUserButton(String name, String mail){
        //waitUntilElement(findByXPath(getXpathToTheNeedfulTeamMember(name, mail) + "//button[@data-delete-team]"));
        click(findByXPath(getXpathToTheNeedfulTeamMember(name, mail) + "//button[@data-delete-team]"));
    }

    public String getMessageText(){return getText(messageTextLocator);}

    public Boolean isNameErrorPresented(){return isElementPresented(nameErrorLocator);}

    public Boolean isEmailErrorPresented(){return isElementPresented(emailErrorLocator);}

    public Boolean isPhoneErrorPresented(){return isElementPresented(phoneErrorLocator);}

}
