package MainPage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
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
        mouseActions(ScheduleMaps);
        //Click on bus
        By busElement = By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/bus-schedules']");
        WebElement bus = driver.findElement(busElement);
        Actions actions1 = new Actions(driver);
        wait(busElement);
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
        WebElement resultLst = driver.findElement(By.cssSelector("a[href='https://www.translink.ca/schedules-and-maps/route/99/direction/1/schedule']"));
        jsExecutor(resultLst);

        //7)In the bus schedule page of “99”,a.	Update the date
        WebElement calenderdateTime = driver.findElement(By.id("schedulestimefilter-startdate"));
       // String currentDate = calenderdateTime.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
//        try{
//            calendar.setTime(sdf.parse(currentDate));
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        calendar.add(Calendar.DAY_OF_MONTH,1);
        String newDate = sdf.format(calendar.getTime());
        String month = newDate.substring(5,7);
        String date = newDate.substring(8,10);
        calenderdateTime.sendKeys(month+date);
        calenderdateTime.sendKeys(Keys.TAB);
        WebElement starttime = driver.findElement(By.id("schedulestimefilter-starttime"));
        starttime.sendKeys("0700AM");
        starttime.sendKeys(Keys.TAB);
        WebElement endtime =  driver.findElement(By.id("schedulestimefilter-endtime"));
        endtime.sendKeys("0830AM");
        driver.findElement(By.cssSelector("button[form='SchedulesTimeFilter']")).click();
        //8)In the schedule list, pick up following stops:a.50913 b.50916 c.58613
        WebElement scheduleList = driver.findElement(By.cssSelector("button[aria-label='No options selected']"));
       jsExecutor(scheduleList);
        //WebElement stopName = driver.findElement(By.cssSelector("label > span"));

        List<WebElement> stops = driver.findElements(By.cssSelector("#StopsPicker-listbox>li"));
        stops.get(6).click();
        stops.get(7).click();
        stops.get(8).click();
        //d.Click on “Selected Stops Only” button
        driver.findElement(By.cssSelector("div[class='contentItem hideOnAPIFail flexContainer flexWrapper useButtons useFontColor'] button[name='ShowSelectedStops']")).click();
        //9)Click on “Add to favourites” button on the right side
        WebElement addtofav = driver.findElement(By.cssSelector("#information > section.CopyMain > div > button"));
        jsExecutor(addtofav);

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
        WebElement managemyFav =driver.findElement(By.cssSelector("div[class='flexContainer flexColumn useContentSpacing useFontColorInfinitely'] a[title=\"Link to 'My Favourites' page on this site\"]"));
        jsExecutor(managemyFav);
        String Fav = driver.findElement(By.cssSelector("div.flexContainer.flexColumn.useAllInfinitely > ul > li > a")).getText();
        if (Fav.equals(scheduleName)) {
            System.out.println("Favourite successfully added");
        }
        else{
            System.out.println("Favourite not added");
        }
        driver.quit();
    }
    public static void jsExecutor(WebElement link) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", link);
        jsExecutor.executeScript("arguments[0].click();", link);
    }
    public static void wait(By ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
    }
    public static void mouseActions(WebElement ele ){
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).build().perform();
    }
}
