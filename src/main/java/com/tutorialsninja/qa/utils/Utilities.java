package com.tutorialsninja.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final String CONFIG_PATH =
            "src/main/java/com/tutorialsninja/qa/config/config.properties";
    public static final String TEST_DATA_PATH =
            "src/main/java/com/tutorialsninja/qa/testdata/testdata.properties";
    private static final String EXCEL_TEST_DATA_PATH =
            "src/main/java/com/tutorialsninja/qa/testdata/TutorialsNinjaTestData.xlsx";

    public static String generateEmailWithTimeStamp(){
        Date date = new Date();
        return "selenium"+date.toString().replace(" ","_")
                .replace(":","_")+"@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName){
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(EXCEL_TEST_DATA_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cells = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cells];

        for (int r = 1; r <= rows; r++){
            for (int c = 0; c < cells; c++){
                if (sheet.getRow(r).getCell(c).getCellType()== CellType.STRING){
                    data[r-1][c] = sheet.getRow(r).getCell(c).getStringCellValue();
                }else if (sheet.getRow(r).getCell(c).getCellType()== CellType.NUMERIC){
                    data[r-1][c] = (int)sheet.getRow(r).getCell(c).getNumericCellValue();
                }else if (sheet.getRow(r).getCell(c).getCellType()== CellType.BOOLEAN){
                    data[r-1][c] = sheet.getRow(r).getCell(c).getBooleanCellValue();
                }
            }
        }
        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testName){
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = "/Screenshots/" +testName+".png";

        try {
            FileHandler.copy(src,new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationScreenshotPath;
    }
}