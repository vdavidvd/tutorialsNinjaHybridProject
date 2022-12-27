package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

    WebDriver driver;

    public AccountSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']/li[last()]/a[.='Success']")
    private WebElement breadcrumbSuccess;

    @FindBy(xpath = "//div[@id='content']/h1[contains(.,'Your Account Has Been Created!')]")
    private WebElement messageSuccess;

    public boolean isDisplayedBreadcrumbSuccess(){
        return breadcrumbSuccess.isDisplayed();
    }

    public boolean isDisplayedMessageSuccess(){
        return messageSuccess.isDisplayed();
    }
}
