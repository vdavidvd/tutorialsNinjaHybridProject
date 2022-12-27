package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Login extends Base{

    LoginPage loginPage;
    AccountPage accountPage;

    @BeforeMethod
    public void setup(){
        loadPropertiesFile();
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));

        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @DataProvider
    public Object[][] supplyTestData(){
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 1, dataProvider = "supplyTestData")
    public void verifyLoginWithValidCredentials(String email, String password){
        accountPage = loginPage.login(email,password);

        Assert.assertTrue(accountPage.isDisplayedBreadcrumbOnMyAccount());
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){
        loginPage.login(Utilities.generateEmailWithTimeStamp()
                ,dataProp.getProperty("invalidPassword"));

        Assert.assertEquals(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText(),
                dataProp.getProperty("emailPasswordNoMatchWarning"));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        loginPage.login(Utilities.generateEmailWithTimeStamp()
                ,prop.getProperty("validPassword"));

        Assert.assertEquals(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText(),
                dataProp.getProperty("emailPasswordNoMatchWarning"));
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        loginPage.login(prop.getProperty("validEmail")
                ,dataProp.getProperty("invalidPassword"));

        Assert.assertEquals(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText(),
                dataProp.getProperty("emailPasswordNoMatchWarning"));
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials(){
        loginPage.login("","");

        Assert.assertEquals(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText(),
                dataProp.getProperty("emailPasswordNoMatchWarning"));
    }
}