package com.tutorialsninja.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MyListeners implements ITestListener {

    ExtentReports extentReports;
    ExtentTest eTest;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        eTest = extentReports.createTest(result.getInstance().getClass().getSimpleName()
                + " > " + result.getName());
        eTest.log(Status.INFO,result.getName()+" started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        eTest.log(Status.PASS,result.getName()+" passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        eTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver,result.getName()));
        eTest.log(Status.INFO,result.getThrowable());
        eTest.log(Status.FAIL,result.getName()+" failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        eTest.log(Status.INFO,result.getThrowable());
        eTest.log(Status.SKIP,result.getName()+" skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        String extentReportPath = "ExtentReports/extentReport.html";
        try {
            Desktop.getDesktop().browse(new File(extentReportPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}