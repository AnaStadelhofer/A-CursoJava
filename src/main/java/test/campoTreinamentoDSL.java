package test;

import core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.DSL;

import static core.DriverConfig.initBrowser;
import static core.DriverConfig.quitBrowser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class campoTreinamentoDSL extends BaseTest {
	
	private DSL dsl;
	
    @Before
    public void setUp() {
		dsl = new DSL();
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
		initBrowser().switchTo().frame("frame1");
		initBrowser().findElement(By.id("frameButton")).click();
		
		
		Alert alert =  initBrowser().switchTo().alert();
		String message = initBrowser().switchTo().alert().getText();

		Assert.assertEquals("Frame OK!", message);
		alert.accept();

		initBrowser().switchTo().defaultContent();
		initBrowser().findElement(By.id("elementosForm:nome")).sendKeys(message);
	}
	
	@Ignore
	@Test
	public void popup() {
		initBrowser().findElement(By.id("buttonPopUpEasy")).click();

		initBrowser().switchTo().window("Popup");
		initBrowser().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		initBrowser().close();
		initBrowser().switchTo().window("");
		initBrowser().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Ignore
	@Test
	public void windoHandler() {
		initBrowser().findElement(By.id("buttonPopUpHard")).click();
		
		// Pega o id da janela atual
		System.out.println(initBrowser().getWindowHandle());
		// Pega o id de todas as janelas
		System.out.println(initBrowser().getWindowHandles());

		initBrowser().switchTo().window((String) initBrowser().getWindowHandles().toArray()[1]);
		initBrowser().findElement(By.tagName("textarea")).sendKeys("batata");
		initBrowser().close();
		initBrowser().switchTo().window((String) initBrowser().getWindowHandles().toArray()[0]);
		initBrowser().findElement(By.tagName("textarea")).sendKeys("batata");
	}
	
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) initBrowser();
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = initBrowser().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}

	@Test
	public void fixedHold() throws InterruptedException {
		dsl.setClick("buttonDelay");
		Thread.sleep(5000);
		dsl.setField("novoCampo", "Preenchendo após a espera");
	}

	@Test
	public void implicitWait() {
		initBrowser().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.setClick("buttonDelay");
		dsl.setField("novoCampo", "Preenchendo após a espera");
	}

	@Test
	public void explicitWait() {
		dsl.setClick("buttonDelay");
		WebDriverWait wait = new WebDriverWait(initBrowser(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.setField("novoCampo", "Preenchendo após a espera");
	}

}
