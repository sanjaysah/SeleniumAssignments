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
        driver.quit();
    }
}
