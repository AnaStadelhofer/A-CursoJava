package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import core.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import page.DSL;
import page.campoTreinamentoPage;

import static core.DriverConfig.quitBrowser;

@RunWith(Parameterized.class)
public class testeRegraCadastro extends BaseTest {

	private WebDriver driver;
	private DSL dsl;
	private campoTreinamentoPage page;
	
	@Parameter
	public String name;
	@Parameter(value=1)
	public String lastname;
	@Parameter(value=2)
	public String gender;
	@Parameter(value=3)
	public List<String> foods;
	@Parameter(value=4)
	public String[] sports;
	@Parameter(value=5)
	public String msg;
	
    @Before
    public void setUp() {
		dsl = new DSL();
    }
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Wagner", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Wagner", "Costa", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras(){
		page.setName(name);
		page.setLastname(lastname);
		if(gender.equals("Masculino")) {
			page.setGenderMan();
		} 
		if(gender.equals("Feminino")) {
			page.setGenderWoman();
		}
		if(foods.contains("Carne")) page.setFavoriteFoodMeet(); 
		if(foods.contains("Pizza")) page.setFavoriteFoodPizza(); 
		if(foods.contains("Vegetariano")) page.setFavoriteFoodVegan(); 
		page.setSports(sports);
		page.realizeRegisterUser();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertText());
	}
}
