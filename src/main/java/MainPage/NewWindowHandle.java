package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class NewWindowHandle {
    static WebDriver driver;
    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("ta1")).sendKeys("New selenium features available");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.thebay.com");
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement closBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#bx-close-inside-2131232.bx-close.bx-close-link.bx-close-inside")));
        driver.findElement(By.cssSelector("button#bx-close-inside-2131232.bx-close.bx-close-link.bx-close-inside"));
        closBtn.click();
        WebElement navcateg = driver.findElement(By.cssSelector("nav > div > ul > li:nth-child(1)"));
        Actions actions = new Actions(driver);
        actions.moveToElement(navcateg).build().perform();
        driver.findElement(By.cssSelector("#autocomplete-0-input")).sendKeys("mango");
        List<WebElement> autosuggestion = driver.findElements(By.cssSelector("div.aa-ItemContentBody > div"));
        autosuggestion.get(0).click();
        WebElement dropdown = driver.findElement(By.cssSelector("#algolia-sort-by-placeholder > select"));
        Select select = new Select(dropdown);
        select.selectByIndex(2);
driver.close();
    }
}
