package com.tutorialsninja.qa.base;

import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    public WebDriver initializeBrowserAndOpenApplication(String browserName){
        if (browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if (browserName.equalsIgnoreCase("edge"))
            driver = new EdgeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }

    public void loadPropertiesFile(){
        prop = new Properties();
        dataProp = new Properties();
        try {
            prop.load(new FileInputStream(Utilities.CONFIG_PATH));
            dataProp.load(new FileInputStream(Utilities.TEST_DATA_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}