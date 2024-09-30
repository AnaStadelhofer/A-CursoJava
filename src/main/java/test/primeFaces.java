package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import page.DSL;

import static core.DriverConfig.quitBrowser;

public class primeFaces {

	private DSL dsl;
	private WebDriver driver;
	
	@After
	public void endTests() {
		quitBrowser();
	}
	
    @Before
    public void setUp() {
		dsl = new DSL();
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
