package MainPage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Popups {
    static WebDriver driver;


    public static void main(String[] args) {
    //pageonload popup
        driver = new ChromeDriver();
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
        String digest = driver.findElement(By.cssSelector("div > h3")).getText();
        System.out.println(digest);

        driver.close();

    }
}
