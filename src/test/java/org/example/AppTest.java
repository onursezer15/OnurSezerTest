package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Vector;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void MyTest() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://ivd.gib.gov.tr/tvd_side/main.jsp?token=d1078f5e3dc646b78d5d4e5842f21e97feb48d366bc7617458b6679dec12675154a01fccc42292bb04d926bc259dbc75e39dd8e202535fd70a7098396c74a6f7";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        interaktifVD(driver, wait);
        odemePlaniNoIleYapilandirma(driver, wait);
        mtvAndTpc(driver, wait);


    }

    public void mtvAndTpc(WebDriver driver, WebDriverWait wait) {
        WebElement mtv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gen__1079\"]")));
        mtv.click();
        WebElement tcknVkn = driver.findElement(By.xpath("//*[@id=\"gen__1634\"]"));
        tcknVkn.sendKeys(tcknGenerate());

        WebElement plaka = driver.findElement(By.xpath("//*[@id=\"gen__1635\"]"));
        plaka.sendKeys("1234567");

        WebElement securityCode = driver.findElement(By.xpath("//*[@id=\"gen__1643\"]"));
        securityCode.sendKeys("1234567");

        WebElement questionClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"gen__1644\"]"))));
        questionClick.click();
        WebElement okClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"runtime-body\"]/div[6]/div[2]/div/div/div/input")));
        okClick.click();
        WebElement closeClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"runtime-body\"]/div[4]/div[1]/div[2]")));
        closeClick.click();
    }

    public void odemePlaniNoIleYapilandirma(WebDriver driver, WebDriverWait wait) {
        WebElement odemePlanı = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gen__1084\"]")));
        odemePlanı.click();
        WebElement relocationFileNo = driver.findElement(By.xpath("//*[@id=\"gen__1618\"]"));
        relocationFileNo.sendKeys("10010101010100101010");

        WebElement tcknVkn = driver.findElement(By.xpath("//*[@id=\"gen__1619\"]"));
        tcknVkn.sendKeys(tcknGenerate());

        WebElement securityCode = driver.findElement(By.xpath("//*[@id=\"gen__1623\"]"));
        securityCode.sendKeys("1234567");

        WebElement questionClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"gen__1624\"]"))));
        questionClick.click();

        WebElement okClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"runtime-body\"]/div[6]/div[2]/div/div/div/input")));
        okClick.click();
        WebElement closeClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"runtime-body\"]/div[4]/div[1]/div[2]")));
        closeClick.click();
    }

    public void interaktifVD(WebDriver driver, WebDriverWait wait) {
        WebElement odemeplani = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gen__1084\"]"))); 
        WebElement tcknVkn = driver.findElement(By.xpath("//*[@id=\"gen__1057\"]"));
        tcknVkn.sendKeys(tcknGenerate());

        WebElement password = driver.findElement(By.xpath("//*[@id=\"gen__1058\"]"));
        password.sendKeys("10010101010");

        WebElement securityCode = driver.findElement(By.xpath("//*[@id=\"gen__1063\"]"));
        securityCode.sendKeys("1234567");

        WebElement loginClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"gen__1067\"]"))));
        loginClick.click();
        WebElement okClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"runtime-body\"]/div[4]/div[2]/div/div/div/input")));
        okClick.click();

    }

    public String tcknGenerate() {
        Vector<Integer> array = new Vector<Integer>();
        Random randomGenerator = new Random();

        array.add(new Integer(1 + randomGenerator.nextInt(9)));

        for (int i = 1; i < 9; i++) array.add(randomGenerator.nextInt(10));

        int t1 = 0;
        for (int i = 0; i < 9; i += 2) t1 += array.elementAt(i);

        int t2 = 0;
        for (int i = 1; i < 8; i += 2) t2 += array.elementAt(i);

        int x = (t1 * 7 - t2) % 10;

        array.add(new Integer(x));

        x = 0;
        for (int i = 0; i < 10; i++) x += array.elementAt(i);

        x = x % 10;
        array.add(new Integer(x));

        String res = "";
        for (int i = 0; i < 11; i++) res = res + Integer.toString(array.elementAt(i));
        return res;
    }

}
