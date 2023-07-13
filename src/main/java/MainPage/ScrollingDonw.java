package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollingDonw {
    static WebDriver driver;
    public static void main(String[] args) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
         driver = new FirefoxDriver(options);
         driver.manage().window().maximize();
         driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.cssSelector("#myBtn")).click();
//        WebElement button = driver.findElement(By.cssSelector("#myBtn"));

//        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
//        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", button);
        String text = driver.findElement(By.cssSelector("#myBtn")).getText();
        System.out.println(text);

    }
}
