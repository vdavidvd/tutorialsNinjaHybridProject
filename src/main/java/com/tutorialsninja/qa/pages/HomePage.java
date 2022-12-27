package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[.='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(xpath = "//a[.='Login']")
    private WebElement loginOption;

    @FindBy(xpath = "//a[.='Register']")
    private WebElement registerOption;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchBoxField;

    @FindBy(xpath = "//div[@id='search']//button")
    private WebElement searchButton;

    public LoginPage navigateToLoginPage(){
        myAccountDropMenu.click();
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage(){
        myAccountDropMenu.click();
        registerOption.click();
        return new RegisterPage(driver);
    }

    public SearchPage searchForProduct(String productName){
        searchBoxField.sendKeys(productName);
        searchButton.click();
        return new SearchPage(driver);
    }
}