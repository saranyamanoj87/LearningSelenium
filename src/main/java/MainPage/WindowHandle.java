package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowHandle {
static WebDriver driver;
    static Set<String> allWindows;
    public static void main(String[] args) {

         driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.linkText("Open a popup window")).click();
        driver.findElement(By.linkText("Blogger")).click();
         allWindows = driver.getWindowHandles();
        iteratorWindow("Basic Web Page Title");
        String childWindow = driver.findElement(By.id("para2")).getText();
        System.out.println(childWindow);
        iteratorWindow("Blogger.com - Create a unique and beautiful blog easily.");
        driver.findElement(By.cssSelector("a[class=\"sign-in ga-header-sign-in\"] span")).click();
        System.out.println("Blogger");
        driver.quit();

        }


    public static void iteratorWindow(String pagetitle){

        Iterator<String> itr = allWindows.iterator();
        while (itr.hasNext()) {
            String win = itr.next();
            driver.switchTo().window(win);
            if (driver.getTitle().equals(pagetitle)) {

                break;
            }

    }
}
}

