package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoading {
    static WebDriver driver;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
        driver.findElement(By.cssSelector("#start  button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading > img")));
        WebElement dynamicloading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish > h4")));
        String dynPageElement = dynamicloading.getText();
        System.out.println(dynPageElement);
        driver.close();

    }
}