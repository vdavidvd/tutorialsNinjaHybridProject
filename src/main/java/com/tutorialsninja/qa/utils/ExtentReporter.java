package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter{

    public static ExtentReports generateExtentReport(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir")
                        +"\\ExtentReports\\extentReport.html");
        sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("TutorialsNinja | QA");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(
                    "src/main/java/com/tutorialsninja/qa/config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL",prop.getProperty("url"));
        extentReports.setSystemInfo("Browser",prop.getProperty("browser"));
        extentReports.setSystemInfo("Email",prop.getProperty("validEmail"));
        extentReports.setSystemInfo("Password",prop.getProperty("validPassword"));
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Operating System Version",System.getProperty("os.version"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("Selenium Version","4.6.0");
        extentReports.setSystemInfo("Tested By","David");

        return extentReports;
    }
}
