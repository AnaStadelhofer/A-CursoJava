package test;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class campoTreinamento {

	private WebDriver driver;

	@After
	public void endTests() {
		driver.quit();
	}
	
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }
	
	@Test
	public void textInput() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste escrito por automação");
		
		Assert.assertEquals("Teste escrito por automação", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
	public void textAreaInput() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste escrito por automação");
		Assert.assertEquals("Teste escrito por automação", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	
	@Test
	public void radioButtonInput() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void checkBoxInput() {
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
	}
	
	@Test
	public void comboInput() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		combo.selectByIndex(2);
		combo.selectByValue("superior");
		combo.selectByVisibleText("Mestrado");
		
		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
	}
	
	@Test
	public void comboUtils() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
	
		Assert.assertEquals(8, options.size());
	}
	
	@Test
	public void comboBoxInput() {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.deselectByVisibleText("Corrida");
		
		Assert.assertEquals(1, combo.getAllSelectedOptions().size());
	}
	
	@Test
	public void button() {
		Assert.assertEquals("Clique Me!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
		driver.findElement(By.id("buttonSimple")).click();
		Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
	}
	
	@Test
	public void linkText() {
		driver.findElement(By.linkText("Voltar")).click();

		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Voltou!"));
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().contains("Voltou!"));
	}
	
	@Test
	public void findSpanAndDiv() {
		driver.findElement(By.tagName("span")).getText().contains("Cuidado onde clica, muitas armadilhas...");
		driver.findElement(By.className("facilAchar")).getText().contains("Cuidado onde clica, muitas armadilhas...");
		driver.findElement(By.tagName("h3")).getText().contains("Campo de Treinamento");
	}
	
	@Test
	public void simpleAlert() {
		driver.findElement(By.id("alert")).click();
		
		Assert.assertEquals("Alert Simples", driver.switchTo().alert().getText());;
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void confirmAlert() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();

		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
		
		driver.findElement(By.id("confirm")).click();		
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
	}
	
	@Test
	public void promptAlert() {
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		
		alert.sendKeys("Batata");
		alert.accept();
		Assert.assertEquals("Era Batata?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
	}
	
	@Test
	public void iframe() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		
		Alert alert =  driver.switchTo().alert();
		String message = driver.switchTo().alert().getText();

		Assert.assertEquals("Frame OK!", message);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(message);
	}
	
	@Test
	public void popup() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void windoHandler() {
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		// Pega o id da janela atual
		System.out.println(driver.getWindowHandle());
		// Pega o id de todas as janelas
		System.out.println(driver.getWindowHandles());
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("batata");
		driver.close();
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("batata");
	}
}
