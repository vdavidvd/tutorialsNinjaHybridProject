package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmField;

    @FindBy(xpath = "//input[@name='newsletter' and @value='1']")
    private WebElement newsletterYesOption;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyPolicyField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement duplicateEmailAddressWarningMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarningMessage;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarningMessage;

    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarningMessage;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailAddressWarningMessage;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarningMessage;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarningMessage;

    public void clickOnNewsletterYesOption(){
        newsletterYesOption.click();
    }

    public void clickOnPrivacyPolicy(){
        privacyPolicyField.click();
    }

    public AccountSuccessPage clickOnContinueButton(){
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public String getDuplicateEmailAddressWarningMessage(){
        return duplicateEmailAddressWarningMessage.getText();
    }

    public String getPrivacyPolicyWarningMessage(){
        return privacyPolicyWarningMessage.getText();
    }

    public String getFirstNameWarningMessage(){
        return firstNameWarningMessage.getText();
    }

    public String getLastNameWarningMessage(){
        return lastNameWarningMessage.getText();
    }

    public String getEmailAddressWarningMessage(){
        return emailAddressWarningMessage.getText();
    }

    public String getTelephoneWarningMessage(){
        return telephoneWarningMessage.getText();
    }

    public String getPasswordWarningMessage(){
        return passwordWarningMessage.getText();
    }

    public void register(String firstNameText, String lastName, String emailAddress,
                         String telephoneNumber, String password){
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(emailAddress);
        telephoneField.sendKeys(telephoneNumber);
        passwordField.sendKeys(password);
        passwordConfirmField.sendKeys(password);
    }
}
