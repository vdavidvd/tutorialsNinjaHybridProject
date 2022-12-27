package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='caption']//a[contains(.,'HP')]")
    private WebElement validHPProduct;

    @FindBy(xpath = "//div[@id='content']//p[last()]")
    private WebElement invalidProduct;

    public boolean isDisplayedValidHPProduct(){
        return validHPProduct.isDisplayed();
    }

    public String getInvalidProductSearchMessage(){
        return invalidProduct.getText();
    }
}
