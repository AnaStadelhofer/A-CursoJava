package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import page.DSL;
import page.campoTreinamentoPage;

import static core.DriverConfig.quitBrowser;

public class cadastradoUsuario {

	private DSL dsl;
	private campoTreinamentoPage page;
	
	@After
	public void endTests() {
		quitBrowser();
	}
	
    @Before
    public void setUp() {
		dsl = new DSL();
		page = new campoTreinamentoPage();
    }
    
    @Test
    public void insertUserSuccess() {
    	page.setName("Ana");
    	page.setLastname("Stadelhofer");
		page.setGenderWoman();
		page.setFavoriteFoodMeet();
		page.realizeRegisterUser();
		page.setEducationMaster();
		page.setSports("Corrida", "Karate");
		Assert.assertTrue(page.getResultRegister().contains("Cadastrado!"));
		Assert.assertTrue(page.getName().contains("Ana"));
		Assert.assertTrue(page.getLastname().contains("Stadelhofer"));
		Assert.assertTrue(page.getGenderWoman());
		Assert.assertTrue(page.getEducation().contains("Mestrado"));
    }
    
    @Test
    public void nameMandatory() {
    	page.setLastname("Stadelhofer");
		page.setGenderWoman();
		page.realizeRegisterUser();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertText());
    }
    
    @Test
    public void lastnameMandatory() {
    	page.setName("Ana");
		page.setGenderWoman();
		page.realizeRegisterUser();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertText());
    }
    
    @Test
    public void genderMandatory() {
    	page.setName("Ana");
    	page.setLastname("Stadelhofer");
		page.realizeRegisterUser();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertText());
    }
}
