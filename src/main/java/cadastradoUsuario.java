import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObjects.DSL;
import PageObjects.campoTreinamentoPage;

public class cadastradoUsuario {

	private DSL dsl;
	private WebDriver driver;
	private campoTreinamentoPage page;
	
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
		page = new campoTreinamentoPage(driver);
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
