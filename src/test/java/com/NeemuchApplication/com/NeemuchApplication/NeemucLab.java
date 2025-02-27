package com.NeemuchApplication.com.NeemuchApplication;

import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NeemucLab {
	    WebDriver driver;
	    WebDriverWait wait;

	    @BeforeTest
	    public void setUp() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @Test(priority = 1)
	    public void launchLabModule() throws Exception {
	    	 driver.get("https://healthandwellness.dhanushhealthcare.com/");
	        driver.findElement(By.id("username")).sendKeys("harilab@dhspltd.com");
	        driver.findElement(By.id("password")).sendKeys("654321");
	        Thread.sleep(8000);
	       // driver.findElement(By.id("captchaText")).sendKeys("Test123");

	        WebElement loginButton = wait.until(
	            ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Login')]")));
	        loginButton.click();
	    }

	    @Test(priority = 2, dependsOnMethods = "launchLabModule")
	    public void labBillingScreen() throws Exception {
	        WebElement labPatientList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Lab Patient List')]")));
	        labPatientList.click();

	        driver.findElement(By.id("search_string")).sendKeys("8074942153");

	        WebElement searchPatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Submit']")));
	        searchPatient.click();

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,250)");

	        WebElement addServices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"patientTable\"]/tbody/tr[1]/td[11]/button[1]")));
	        addServices.click();
	        Thread.sleep(3000);
	    }

	    @Test(priority = 3, dependsOnMethods = "launchLabModule")
	    public void labSampleCollection() throws Exception {
	        WebElement laboratoryModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#m-24']")));
	        laboratoryModule.click();

	        WebElement labSampleCollection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Lab Sample Collection')]")));
	        labSampleCollection.click();

	        WebElement sampleCollection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sample Collection')]")));
	        sampleCollection.click();
	        Thread.sleep(2000);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,500)");

	        WebElement sampleSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
	        sampleSave.click();
	        Thread.sleep(2000);
	        
	        WebElement sampleApproved = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Approved')]")));
	        sampleApproved.click();

	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	        
	    }

	    @Test(priority = 4, dependsOnMethods = "launchLabModule")
	    public void labResultEntryScreen() throws Exception {
	        WebElement testResultEntry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Test Result Entry')]")));
	        testResultEntry.click();

	        WebElement exportTestResultData = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Export The Result Entry Data')]")));
	        exportTestResultData.click();
	        Thread.sleep(80000);
	        
	        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
	        String filePath = Paths.get("C:/Users/Hariprasad R/Downloads/lab_Result_data_entry.xlsx")
	            .toAbsolutePath()
	            .toString();
	        fileInput.sendKeys(filePath);
	        Thread.sleep(3000);

	        WebElement generateOverallReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Generate Over All Report')]")));
	        generateOverallReport.click();
	    }

	@AfterTest
	    public void tearDown() {
		System.out.println("Executed Successfully");
	        if (driver != null) {
	            driver.quit();
	        }
	    
	}}


