
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
import java.util.function.Function;

public class LoginAddEmployee {
    public static void main(String[] args) {
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
        // Add Employee button click
        WebElement addEmployee = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/button"));
            }
        });
        driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/button")).click();
        //Assertion
        Assert.assertEquals(driver.getCurrentUrl(),"https://awesomeqa.com/hr/web/index.php/pim/addEmployee");
        //Fill Add Employee form
        WebElement header = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
            }
        });
        String actual = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/h6")).getText();
        Assert.assertEquals(actual, "Add Employee");

        // Fill Employee form and submit
        driver.findElement(By.name("firstName")).sendKeys("Sanjay");
        driver.findElement(By.name("middleName")).sendKeys("Kumar");
        driver.findElement(By.name("lastName")).sendKeys("Sah");
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

        //Verify employee is added successfully in UI
        WebElement EmpName = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-name']/h6"));
            }
        });
        String actualName = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-name']/h6")).getText();
        Assert.assertEquals(actualName, "Sanjay Sah");

        //driver.quit();
    }
}
