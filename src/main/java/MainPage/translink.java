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
        //Launch any browser
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        //goto https://www.translink.ca/
        driver.get("https://www.translink.ca/");
        //2)Expand the top menu, select “Schedules and Maps > Bus”
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
        //3)Validate the page is loaded
        String result = driver.findElement(By.id("bus-schedules")).getText();
        if (result.equals("Bus Schedules")) {
            System.out.println("Page loaded successfully");
        }
        //4)Locate input area “Search by transit mode, route # or name”
        //5)Input “99” and click “Find Schedule” button
        driver.findElement(By.id("find-schedule-searchbox")).sendKeys("99");
        //click on Find schedule
        driver.findElement(By.cssSelector("section:nth-child(4) > div:nth-child(2) > button:nth-child(2)")).click();
        //6)In the result list, select and click “#99 – UBC B-Line”
        driver.findElement(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/route/99/direction/1/schedule']")).click();
        //7)In the bus schedule page of “99”,a.	Update the date
        //8)In the schedule list, pick up following stops:a.50913 b.50916 c.58613
        driver.findElement(By.cssSelector("button[aria-label='No options selected']")).click();
        //WebElement stopName = driver.findElement(By.cssSelector("label > span"));
        List<WebElement> stops = driver.findElements(By.cssSelector("#StopsPicker-listbox>li"));
        stops.get(6).click();
        stops.get(7).click();
        stops.get(8).click();
        //d.Click on “Selected Stops Only” button
        driver.findElement(By.cssSelector("section.CopyMain > div:nth-child(5) > button:nth-child(3)")).click();
        //9)Click on “Add to favourites” button on the right side
        driver.findElement(By.cssSelector(".AddDelGTFSFav[data-type='unstyledButton'][data-faux-type='link']")).click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            driver.switchTo().window(win);
        }
        //10)Rename it to be “99 UBC B-Line – Morning Schedule” and click on “Add to favourites”
        driver.findElement(By.id("newfavourite")).clear();
        String scheduleName = "99 UBC B-Line – Morning Schedule";
        driver.findElement(By.id("newfavourite")).sendKeys(scheduleName);
        driver.findElement(By.cssSelector("dialog[id='add-to-favourites_dialog'] button[value='confirm']")).click();
        //11)Click on “Manage my favourites” button on the right side to validate the item just added.
        driver.findElement(By.cssSelector("#information > section.CopyMain > div > a:nth-child(3)")).click();
        String Fav = driver.findElement(By.cssSelector("div.flexContainer.flexColumn.useAllInfinitely > ul > li > a")).getText();
        if (Fav.equals(scheduleName)) {
            System.out.println("Favourite successfully added");
        }
        else{
            System.out.println("Favourite not added");
        }
        driver.quit();
    }
}
