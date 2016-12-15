package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class CalcTest extends BaseTest{
    public CalcTest() {
        super("C:\\Users\\user001\\Downloads\\com.android2.calculator3-6.0-APK4Fun.com.apk"
                , "com.xlythe.calculator.material.Calculator");
    }

    @Test
    public void testCalc() throws Exception {
        WebElement seven = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@index='0']"));
        seven.click();
        WebElement eight = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@index='1']"));
        eight.click();

        WebElement multiply = driver.findElement(By.xpath("//android.widget.Button[@content-desc='times']"));
        multiply.click();

        WebElement five = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@text='5']"));
        five.click();
        WebElement six = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@text='6']"));
        six.click();
        WebElement dot = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@text='.']"));
        dot.click();
        WebElement zero = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@index='10']"));
        zero.click();
        WebElement nine = driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']//android.widget.Button[@text='9']"));
        nine.click();

        WebElement equals = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='equals']"));
        //equals.click();
        WebElement resultText = driver.findElement(By.xpath("//android.widget.TextView[@index='1']"));

        String expected ="4,375.02";
        Assert.assertEquals(resultText.getText(),expected);
        System.out.println( );
    }
}
