package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class translink {
    static WebDriver driver;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        //Navigate to URL
        driver.get("https://www.translink.ca/");
        //Click on Schedule and Maps
        WebElement ScheduleMaps = driver.findElement(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ScheduleMaps).build().perform();
        //Click on bus
        WebElement bus = driver.findElement(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/bus-schedules']"));
        Actions actions1 = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/bus-schedules']")));
        actions1.moveToElement(bus).build().perform();
        bus.click();
        //Verified page loaded succesfully
        String result = driver.findElement(By.id("bus-schedules")).getText();
        if (result.equals("Bus Schedules")) {
            System.out.println("Page loaded successfully");
        }
        //search 99
        driver.findElement(By.id("find-schedule-searchbox")).sendKeys("99");
        //click on Find schedule
        driver.findElement(By.cssSelector("section:nth-child(4) > div:nth-child(2) > button:nth-child(2)")).click();
        //click on #99-UBC B-Line
       WebElement ubcbline = driver.findElement(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/route/99/direction/1/schedule']"));
        String route = ubcbline.getText();
        ubcbline.click();
        //Schdulelist 3 stops picked
        driver.findElement(By.cssSelector("button[aria-label='No options selected']")).click();
        //WebElement stopName = driver.findElement(By.cssSelector("label > span"));
        List<WebElement> stops = driver.findElements(By.cssSelector("#StopsPicker-listbox>li"));
        stops.get(6).click();
        stops.get(7).click();
        stops.get(8).click();
        //click on selected Stops only
        driver.findElement(By.cssSelector("section.CopyMain > div:nth-child(5) > button:nth-child(3)")).click();
        //Add to favourites
        driver.findElement(By.cssSelector(".AddDelGTFSFav[data-type='unstyledButton'][data-faux-type='link']")).click();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> itr = allWindows.iterator();
        while (itr.hasNext()) {
            String win = itr.next();
            driver.switchTo().window(win);
        }
        driver.findElement(By.id("newfavourite")).clear();
        driver.findElement(By.id("newfavourite")).sendKeys(route);
        driver.findElement(By.cssSelector("dialog[id='add-to-favourites_dialog'] button[value='confirm']")).click();
        //Goto Manage fav to validate favourited added or not
        driver.findElement(By.cssSelector("#information > section.CopyMain > div > a:nth-child(3)")).click();
        String Fav = driver.findElement(By.cssSelector("div.flexContainer.flexColumn.useAllInfinitely > ul > li > a")).getText();
//        System.out.println(Fav);
//        System.out.println(route);
        if (Fav.equals(route)) {
            System.out.println("Favourite succesfully added");
        }
        else{
            System.out.println("Favourite not added");
        }
        driver.quit();
    }
}
