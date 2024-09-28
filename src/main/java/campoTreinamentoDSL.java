import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import PageObjects.DSL;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

public class campoTreinamentoDSL {
	
	private WebDriver driver;
	private DSL dsl;

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
		dsl = new DSL(driver);
    }
	
	@Test
	public void textInput() {
		dsl.setField("elementosForm:nome", "Ana");
		Assert.assertEquals("Ana", dsl.getField("elementosForm:nome", "value"));
	}
	
	@Test
	public void doubleText() {
		dsl.setField("elementosForm:nome", "Ana");
		Assert.assertEquals("Ana", dsl.getField("elementosForm:nome", "value"));
		dsl.setField("elementosForm:nome", "Stadelhofer");
		Assert.assertEquals("Stadelhofer", dsl.getField("elementosForm:nome", "value"));
	}
	
	@Test
	public void textAreaInput() {
		dsl.setField("elementosForm:sugestoes", "Teste escrito por automação");
		Assert.assertEquals("Teste escrito por automação", dsl.getField("elementosForm:sugestoes", "value"));
	}
	
	@Test
	public void radioButtonInput() {
		dsl.setRadioAndCheck("elementosForm:sexo:0");
		Assert.assertTrue(dsl.getRadioAndCheck("elementosForm:sexo:0"));
	}
	
	@Test
	public void checkBoxInput() {
		dsl.setRadioAndCheck("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.getRadioAndCheck("elementosForm:comidaFavorita:1"));
	}
	
	@Test
	public void comboInput() {
		dsl.setComboBox("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.getComboSelected("elementosForm:escolaridade"));
	}
	
	@Test
	public void comboUtils() {
		dsl.setComboBoxMultiplo("elementosForm:esportes", "Corrida");
	
		Assert.assertEquals(5, dsl.getAllInputSelect("elementosForm:esportes"));
	}
	
	@Test
	public void comboBoxInput() {
		dsl.setComboBoxMultiplo("elementosForm:esportes", "Natacao");
		dsl.setComboBoxMultiplo("elementosForm:esportes", "Corrida");
		dsl.undoCombo("elementosForm:esportes", "Corrida");
		
		Assert.assertEquals(1, dsl.getSelectedItens("elementosForm:esportes").size());
	}
	
	@Test
	public void button() {
		Assert.assertEquals("Clique Me!", dsl.getText("buttonSimple", "value"));
		dsl.setClick("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.getText("buttonSimple", "value"));
	}
	
	@Test
	public void linkText() {
		dsl.setClickLink("Voltar");
		
		Assert.assertTrue(dsl.containsElement(By.tagName("body")).contains("Voltou!"));
		Assert.assertTrue(dsl.containsElement(By.id("resultado")).contains("Voltou!"));
	}
	
	@Test
	public void findSpanAndDiv() {
		Assert.assertTrue(dsl.containsElement(By.className("facilAchar")).contains("Cuidado onde clica, muitas armadilhas..."));
		Assert.assertTrue(dsl.containsElement(By.tagName("h3")).contains("Campo de Treinamento"));
	}
	
	// ALERTS
	@Test
	public void simpleAlert() {
		dsl.setClick("alert");
		
		String value = dsl.alertText();
		Assert.assertEquals("Alert Simples", value);
		dsl.setField("elementosForm:nome", value);
	}
	
	@Test
	public void confirmAlert() {
		dsl.setClick("confirm");
		
		Assert.assertEquals("Confirm Simples", dsl.alertConfirm());
		Assert.assertEquals("Confirmado", dsl.alertConfirm());
		
		dsl.setClick("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertDismiss());
		Assert.assertEquals("Negado", dsl.alertDismiss());
		
	}
	
	@Test
	public void promptAlert() {
		dsl.setClick("prompt");
		dsl.alertPrompt("Batata");

		Assert.assertEquals("Era Batata?", dsl.alertConfirm());
		Assert.assertEquals(":D", dsl.alertConfirm());
	}
	
	@Ignore
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
	
	@Ignore
	@Test
	public void popup() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Ignore
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
	
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
	
}
