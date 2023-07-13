package MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DynamicTable {
    static WebDriver driver;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/web-table-element.php");
        //Fetch number of rows and columns from Dynamic WebTable
        //find the webtable element
        WebElement table = driver.findElement(By.cssSelector("#leftcontainer > table > tbody"));
        List<WebElement> allrows = table.findElements(By.tagName("tr"));
        System.out.println("Number of rows in this table is" + allrows.size());
        List<WebElement> allColumns = table.findElements(By.cssSelector(" tr:nth-child(1) > td"));
        System.out.println("Number of columns in this table is" + allColumns.size());
         //Fetch cell value of a particular row and column of the Dynamic Table
        //we need 3rd row of the table and its second cell’s data
        WebElement thirdrow = table.findElement(By.cssSelector("tr:nth-child(3)"));
        System.out.println(thirdrow.getText());
        WebElement cell = thirdrow.findElement(By.cssSelector("td:nth-child(3)"));
        System.out.println(cell.getText());
        //Get all the values of a Dynamic Table
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://demo.guru99.com/test/table.html");
        WebElement dynamicTable = driver.findElement(By.cssSelector("body > table > tbody"));
        List<WebElement> rowofDynamicTable = dynamicTable.findElements(By.tagName("tr"));
        int count = 0;
        int colcount = 0;
        for(WebElement row : rowofDynamicTable){
            count++;
            List<WebElement> colofDynamicTable = row.findElements(By.tagName("td"));
            for(WebElement col : colofDynamicTable){
                colcount++;
                System.out.println("value of row" + " " + count + " " + "column" + " " + colcount + " " + col.getText());
            }
            colcount = 0;
        }


       }

    }
