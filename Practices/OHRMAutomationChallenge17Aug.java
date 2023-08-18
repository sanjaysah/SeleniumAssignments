package com.thetestingacademy.july22;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class OHRMAutomationChallenge17Aug {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        //Login
        driver.get("https://awesomeqa.com/hr/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Hacker@4321");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assertion
        Assert.assertEquals(driver.getCurrentUrl(),"https://awesomeqa.com/hr/web/index.php/pim/viewEmployeeList");

        // Find Employee with expected name and Status and delete it -
        // Here Clicking Cancel instead so as to not delete data
        findEmployeeStatus(driver,"Aman","Terminated");


        //driver.quit();
    }

    public static int getRowsNo(WebDriver driver){
        int rows;
        List<WebElement> rowsElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
        rows = rowsElements.size();
        return rows;
    }

    public static int getColsNo(WebDriver driver){
        int cols;
        List<WebElement> colsElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div[1]/div/div"));
        cols = colsElements.size();
        return cols;
    }

    public static String findEmployeeStatus(WebDriver driver, String name, String status) throws InterruptedException {
        int rows = getRowsNo(driver);
        int cols = getColsNo(driver);
        List<WebElement> rowsElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
        List<WebElement> colsElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div[1]/div/div"));

        String path1 = "//div[@class='oxd-table-body']/div[";
        String path2 = "]/div/div[";
        String path3 = "]";
        for(int i=1;i<rows;i++){
            for(int j=2;j<cols;j++){
                String dynamicXpath = path1+i+path2+j+path3;
                WebElement nameE = driver.findElement(By.xpath(dynamicXpath));
                if(nameE.getText().equals(name)){
                    String pathS = dynamicXpath+"/following-sibling::div[3]";
                    WebElement eStatus = driver.findElement(By.xpath(pathS));
                    if(eStatus.getText().equals(status)){
                        System.out.println("Row: "+i+" Col: "+j);
                        String pathButton = pathS+"/following-sibling::div[3]/div/button[1]";
                        driver.findElement(By.xpath(pathButton)).click();
                        String pathCancelButton = "//div[@class='orangehrm-modal-footer']/button[1]";
                        String pathDeleteButton = "//div[@class='orangehrm-modal-footer']/button[2]";
                        //Click Cancel Button
                        Thread.sleep(3000);
                        driver.findElement(By.xpath(pathCancelButton)).click();
                    }
                }


            }
        }

        return status;
    }
}

