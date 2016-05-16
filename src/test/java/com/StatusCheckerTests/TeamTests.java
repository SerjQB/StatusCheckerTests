package com.StatusCheckerTests;
import org.testng.Assert;
import org.testng.annotations.*;
import com.StatusCheckerPages.*;


public class TeamTests extends BaseTest {
    @Test
    public void addValidUser() {
        String name = "Tester";
        String phone = "+(11) 111-111-11-11";
        String mail = getUniqueValue() + "@mail.com";
        String successMessage = "Adding of new team member was successful";

        TeamsPage teamsPage = new TeamsPage(driver);
        teamsPage.visit();
        teamsPage.addNewTeamMember(name, phone, mail);
        Assert.assertTrue(teamsPage.isSuccessMessagePresented());
        Assert.assertEquals(teamsPage.getMessageText(), successMessage);

        teamsPage.closeMessage();
        refreshPage();
        Assert.assertTrue(teamsPage.isCreatedTeamMemberExist(name, phone, mail));

        teamsPage.clickDeleteUserButton(name, phone, mail);
    }

    @Test
    public void addValidUserWithoutPhone() {
        String name = "Tester";
        String mail = getUniqueValue() + "@mail.com";
        String successMessage = "Adding of new team member was successful";

        TeamsPage teamsPage = new TeamsPage(driver);
        teamsPage.visit();
        teamsPage.addNewTeamMember(name, mail);
        Assert.assertTrue(teamsPage.isSuccessMessagePresented());
        Assert.assertEquals(teamsPage.getMessageText(), successMessage);

        teamsPage.closeMessage();
        refreshPage();
        Assert.assertTrue(teamsPage.isCreatedTeamMemberExist(name, mail));

        teamsPage.clickDeleteUserButton(name, mail);
    }

    @Test
    public void editUser() {
        String name = "Tester";
        String phone = "+(11) 111-111-11-11";
        String mail = getUniqueValue() + "@mail.com";
        String newName = "TesterQA";
        String newPhone = "+(11) 111-111-11-00";
        String newMail = getUniqueValue() + "@mail.com";
        String successMessage = "Team member has been successfully updated!";

        TeamsPage teamsPage = new TeamsPage(driver);
        teamsPage.visit();
        teamsPage.addNewTeamMember(name, phone, mail);
        teamsPage.closeMessage();
        refreshPage();

        teamsPage.openEditMemberForm(name, phone, mail);
        teamsPage.fillTheFields(newName, newPhone, newMail);
        teamsPage.clickSaveTeamMemberButton();
        Assert.assertEquals(teamsPage.getMessageText(), successMessage);
        teamsPage.closeMessage();
        refreshPage();

        Assert.assertTrue(teamsPage.isCreatedTeamMemberExist(newName, newPhone, newMail));
        teamsPage.clickDeleteUserButton(newName, newPhone, newMail);
    }

    @Test
    public void deleteUser() {
        String name = "Tester";
        String phone = "+(11) 111-111-11-11";
        String mail = getUniqueValue() + "@mail.com";
        String successMessage = "Removing of team member was successful";

        TeamsPage teamsPage = new TeamsPage(driver);
        teamsPage.visit();
        teamsPage.addNewTeamMember(name, phone, mail);
        Assert.assertTrue(teamsPage.isSuccessMessagePresented());
        teamsPage.closeMessage();

        teamsPage.clickDeleteUserButton(name, phone, mail);
        refreshPage();
        Assert.assertFalse(teamsPage.isCreatedTeamMemberExist(name, phone, mail));
    }

    @Test
    public void checkFormValidation() {
        String incorrectName = "123/*";
        String incorrectPhone = "+(11) 111-111-11";
        String incorrectMail = "mail.com";

        TeamsPage teamsPage = new TeamsPage(driver);
        teamsPage.visit();
        teamsPage.clickAddTeamMemberButton();
        teamsPage.fillRequiredFields("", "");
        teamsPage.clickSaveTeamMemberButton();
        Assert.assertTrue(teamsPage.isNameErrorPresented());
        Assert.assertTrue(teamsPage.isEmailErrorPresented());

        teamsPage.fillTheFields(incorrectName, incorrectPhone, incorrectMail);
        teamsPage.clickSaveTeamMemberButton();
        Assert.assertTrue(teamsPage.isNameErrorPresented());
        Assert.assertTrue(teamsPage.isEmailErrorPresented());
        Assert.assertTrue(teamsPage.isPhoneErrorPresented());
    }


}