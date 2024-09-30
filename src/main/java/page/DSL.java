package page;


import java.util.ArrayList;
import java.util.List;

import static core.DriverConfig.initBrowser;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	public void setField(String idField, String message) {
		initBrowser().findElement(By.id(idField)).clear();
		initBrowser().findElement(By.id(idField)).sendKeys(message);
	}
	
	public String getField(String idField, String value) {
		return initBrowser().findElement(By.id(idField)).getAttribute(value);
	}
	
	// Radio Button && Check Box //
	public void setRadioAndCheck(String idField) {
		initBrowser().findElement(By.id(idField)).click();
	}
	
	public Boolean getRadioAndCheck(String idField) {
		return initBrowser().findElement(By.id(idField)).isSelected();
	}
	
	// Combo Box ||
	public void setComboBox(String idField, String value) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);
	}
	
	public String getComboSelected(String idField) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	// Combo Box Múltiplo
	public void setComboBoxMultiplo(String idField, String value) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		
		combo.selectByVisibleText(value);
	}
	
	public void undoCombo(String idField, String value) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		combo.deselectByVisibleText(value);
	}
	
	public int getAllInputSelect(String idField) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		return options.size();
	}
	
	public List<String> getSelectedItens(String idField) {
		WebElement element = initBrowser().findElement(By.id(idField));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> value = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			value.add(opcao.getText());
		}
		return value;
	}
	
	// Clicar em elementos //
	public void setClick(String idField) {
		initBrowser().findElement(By.id(idField)).click();
	}
	
	public String getText(String idField, String value) {
		return initBrowser().findElement(By.id(idField)).getAttribute(value);
	}
	
	// Interagir com links
	public void setClickLink(String idField) {
		initBrowser().findElement(By.linkText(idField)).click();
	}
	
	public String containsElement(By typeTag) {
		return initBrowser().findElement(typeTag).getText();
	}	
	
	// ALERTS //
	public String alertText(){
		Alert alert = initBrowser().switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
	}
	
	public String alertConfirm() {
		Alert alert = initBrowser().switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
	}
	
	public String alertDismiss() {
		Alert alert = initBrowser().switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;
	}
	
	public void alertPrompt(String text) {
		Alert alert = initBrowser().switchTo().alert();
		alert.sendKeys(text);
		alert.accept();
	}
	
	
	// TABELA XPATH
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = initBrowser().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}

}
