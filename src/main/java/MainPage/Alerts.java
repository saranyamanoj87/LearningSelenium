package MainPage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Alerts {
    static WebDriver driver;
    static Alert alert;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("alert1")).click();
        alertWaitMethod();
        alert.accept();
        //alert.dismiss();
        driver.findElement(By.id("confirm")).click();
        alertWaitMethod();
        alert.dismiss();
        driver.findElement(By.id("ta1")).sendKeys("Alert test");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://the-internet.herokuapp.com/");
       driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.cssSelector("li:nth-child(1) > button")).click();
        alertWaitMethod();
        alert.accept();
        //prompt alert
       driver.findElement(By.cssSelector("li:nth-child(3) > button")).click();
        alertWaitMethod();
        alert.sendKeys("okay prompt");
        alert.accept();
        String promptInput = driver.findElement(By.id("result")).getText();
        if(promptInput.contains("okay prompt")) {
            System.out.println(promptInput);
        }
        driver.close();

    }
    public static void alertWaitMethod(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
         alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        System.out.println(alertMessage);
    }
}
