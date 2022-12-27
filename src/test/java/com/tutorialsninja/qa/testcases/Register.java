package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Register extends Base {

    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    @BeforeMethod
    public void setup(){
        loadPropertiesFile();
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));

        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields(){
        registerPage.register(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber")
        ,prop.getProperty("validPassword"));
        registerPage.clickOnPrivacyPolicy();
        accountSuccessPage = registerPage.clickOnContinueButton();

        Assert.assertTrue((accountSuccessPage.isDisplayedBreadcrumbSuccess() &&
                accountSuccessPage.isDisplayedMessageSuccess()));
    }

    @Test(priority = 2)
    public void verifyRegisteringAnAccountByProvidingAllFields(){
        registerPage.register(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber")
                ,prop.getProperty("validPassword"));

        registerPage.clickOnNewsletterYesOption();
        registerPage.clickOnPrivacyPolicy();
        accountSuccessPage = registerPage.clickOnContinueButton();

        Assert.assertTrue((accountSuccessPage.isDisplayedBreadcrumbSuccess() &&
                accountSuccessPage.isDisplayedMessageSuccess()));
    }

    @Test(priority = 3)
    public void verifyRegisteringAnAccountWithExistingEmailAddress(){
        registerPage.register(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),
                prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber")
                ,prop.getProperty("validPassword"));

        registerPage.clickOnPrivacyPolicy();
        registerPage.clickOnContinueButton();

        Assert.assertEquals(registerPage.getDuplicateEmailAddressWarningMessage(),
                dataProp.getProperty("duplicateEmailWarning"));
    }

    @Test(priority = 4)
    public void verifyRegisteringAnAccountWithoutFillingAnyDetails(){
        registerPage.clickOnContinueButton();

        Assert.assertEquals(registerPage.getPrivacyPolicyWarningMessage()
                ,dataProp.getProperty("privacyPolicyWarning"));

        Assert.assertEquals(registerPage.getFirstNameWarningMessage(),
                dataProp.getProperty("firstNameWarning"));

        Assert.assertEquals(registerPage.getLastNameWarningMessage()
                ,dataProp.getProperty("lastNameWarning"));

        Assert.assertEquals(registerPage.getEmailAddressWarningMessage(),
                dataProp.getProperty("emailWarning"));

        Assert.assertEquals(registerPage.getTelephoneWarningMessage(),
                dataProp.getProperty("telephoneWarning"));

        Assert.assertEquals(registerPage.getPasswordWarningMessage()
                ,dataProp.getProperty("passwordWarning"));
    }
}