package br.mp.mpf.leilao.uitests.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.mp.mpf.leilao.uitests.listagem.ListagemLeilaoAbertosPage;

public class LoginSeleniumSuiteTest {

	private WebDriver driver;

	@Before
	public void criaDriver() {
		driver = new ChromeDriver();
	}

	@After
	public void fechaDriver() {
		driver.quit();
	}

	@Test
	public void usuarioCadastradoDeveFazerLoginComSucesso() {
		/*driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("joaosilva@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("input[type='submit']")).click();*/
		ListagemLeilaoAbertosPage homePage =
			new LoginPage(driver).visita("http://localhost:8080/leilao").autentica("joaosilva@mpf.mp.br", "123456");

		assertTrue(homePage.isValida());
	}

	@Test
	public void usuarioNaoCadastradoNaoDeveConseguirFazerLogin() {
		/*driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("andrethiago@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("input[type='submit']")).click();*/

		ListagemLeilaoAbertosPage homePage =
			new LoginPage(driver).visita("http://localhost:8080/leilao").autentica("andrethiago@mpf.mp.br", "123456");

		assertFalse(homePage.isValida());
	}

}
