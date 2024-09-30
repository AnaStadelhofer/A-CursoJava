package suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import test.*;

import static core.DriverConfig.closeBrowser;
import static core.DriverConfig.quitBrowser;

@RunWith(Suite.class)
@SuiteClasses({
	//testeRegraCadastro.class,
	//cadastradoUsuario.class,
	campoTreinamentoDSL.class,
	//primeFaces.class
})
public class suiteTest {

	@AfterClass
	public static void closeBrowserClass() {
		if (!closeBrowser) {
			quitBrowser();
		}
	}

}
