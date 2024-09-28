package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class campoTreinamentoPage {

	private DSL dsl;

	public campoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setName(String name) {
		dsl.setField("elementosForm:nome", name);
	}
		
	public void setLastname(String lastname) {
		dsl.setField("elementosForm:sobrenome", lastname);
	}
	
	public void setGenderWoman() {
		dsl.setRadioAndCheck("elementosForm:sexo:1");
	}
	
	public void setGenderMan() {
		dsl.setRadioAndCheck("elementosForm:sexo:0");
	}
	
	public void realizeRegisterUser() {
		dsl.setClick("elementosForm:cadastrar");
	}
	
	public void setFavoriteFoodMeet() {
		dsl.setRadioAndCheck("elementosForm:comidaFavorita:0");
	}
	
	public void setFavoriteFoodChicken() {
		dsl.setRadioAndCheck("elementosForm:comidaFavorita:1");
	}
	
	public void setFavoriteFoodPizza() {
		dsl.setRadioAndCheck("elementosForm:comidaFavorita:2");
	}
	
	public void setFavoriteFoodVegan() {
		dsl.setRadioAndCheck("elementosForm:comidaFavorita:3");
	}
	
	public void setSports(String... values) {
		for(String value: values)
			dsl.setComboBoxMultiplo("elementosForm:esportes", value);
	}
	
	public void setEducationMaster() {
		dsl.setComboBox("elementosForm:escolaridade", "Mestrado");
	}
	
	// GETS
	public String getName() {
		return dsl.containsElement(By.id("descNome"));
	}
	
	public String getLastname() {
		return dsl.containsElement(By.id("descSobrenome"));
	}
	
	public String getResultRegister() {
		return dsl.containsElement(By.id("resultado"));
	}
	
	public Boolean getGenderMan() {
		return dsl.getRadioAndCheck("elementosForm:sexo:0");
	}
	
	public Boolean getGenderWoman() {
		return dsl.getRadioAndCheck("elementosForm:sexo:1");
	}
	
	public Boolean getFavoriteFoodMeetSelect() {
		return dsl.getRadioAndCheck("elementosForm:comidaFavorita:0");
	}
	
	public Boolean getFavoriteFoodChicken() {
		return dsl.getRadioAndCheck("elementosForm:comidaFavorita:1");
	}
	
	public Boolean getFavoriteFoodPizza() {
		return dsl.getRadioAndCheck("elementosForm:comidaFavorita:2");
	}
	
	public Boolean getFavoriteFoodVegan() {
		return dsl.getRadioAndCheck("elementosForm:comidaFavorita:3");
	}
	
	public String getEducation() {
		return dsl.getComboSelected("elementosForm:escolaridade");
	}
}


