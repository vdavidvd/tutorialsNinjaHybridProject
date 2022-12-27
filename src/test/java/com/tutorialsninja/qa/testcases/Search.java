package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {

    HomePage homePage;
    SearchPage searchPage;

    @BeforeMethod
    public void setup(){
        loadPropertiesFile();
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        searchPage = homePage.searchForProduct(dataProp.getProperty("validProduct"));

        Assert.assertTrue(searchPage.isDisplayedValidHPProduct());
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct(){
        searchPage = homePage.searchForProduct(dataProp.getProperty("invalidProduct"));

        Assert.assertEquals(searchPage.getInvalidProductSearchMessage(),
                dataProp.getProperty("noProductTextInSearchResults"));
    }

    @Test(priority = 3)
    public void verifySearchWithoutProvidingProductName(){
        searchPage = homePage.searchForProduct("");

        Assert.assertEquals(searchPage.getInvalidProductSearchMessage(),
                dataProp.getProperty("noProductTextInSearchResults"));
    }
}