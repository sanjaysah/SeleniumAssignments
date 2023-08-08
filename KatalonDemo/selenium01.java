import org.testng.Assert;

import java.time.Duration;

public class KatalonDemo {
    public static void main(String[] args) {
      //Setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
      //Implicit Wait
      //First page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.findElement(By.id("btn-make-appointment")).click();
      //Assertion
        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/profile.php#login");
      //Second Page
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).submit();
      //Assertion Third page
        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");

      //Appointment Page
        WebElement dropdown = driver.findElement(By.id("combo_facility"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Seoul CURA Healthcare Center");
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        driver.findElement(By.id("radio_program_medicare")).click();
        driver.findElement(By.id("txt_visit_date")).sendKeys("04/09/2023");
        driver.findElement(By.id("txt_comment")).sendKeys("I want to get better!!");
        driver.findElement(By.id("btn-book-appointment")).submit();
    // Assert Appointment message
        String appointmentconfirmation = driver.findElement(By.xpath("//h2[text()='Appointment Confirmation']")).getText();
        Assert.assertEquals(appointmentconfirmation,"Appointment Confirmation");
        
        driver.quit();
    }
}
