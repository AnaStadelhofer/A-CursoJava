import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import PageObjects.DSL;

public class primeFaces {

	private DSL dsl;
	private WebDriver driver;
	
	@After
	public void endTests() {
		//driver.quit();
	}
	
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		dsl = new DSL(driver);
    }
    
    @Test
    public void radioButton() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=a68fb");
    	driver.findElement(By.xpath("//input[@id=\"j_idt249:line:0\"]/../../div[contains(@class, 'ui-state-default')]")).click();
    	Assert.assertTrue(driver.findElement(By.id("j_idt249:line:0")).isSelected());
    }
    
    @Test
    public void selectButton() {
    	driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=a68fb");
    	driver.findElement(By.id("j_idt248:option")).click();
		driver.findElement(By.id("j_idt248:option_1")).click();
		Assert.assertTrue(dsl.containsElement(By.id("j_idt248:option_label")).contains("Option1"));
    }
	
}
