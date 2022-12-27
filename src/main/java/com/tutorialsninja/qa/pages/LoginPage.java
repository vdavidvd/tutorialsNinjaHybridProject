package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-email")
    private WebElement emailAddressField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emailPasswordNotMatchingWarning;

    public AccountPage login(String emailText, String passwordText){
        emailAddressField.sendKeys(emailText);
        passwordField.sendKeys(passwordText);
        loginButton.click();
        return new AccountPage(driver);
    }

    public String retrieveEmailPasswordNotMatchingWarningMessageText(){
        return emailPasswordNotMatchingWarning.getText();
    }
}