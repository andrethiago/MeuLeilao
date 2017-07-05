package br.mp.mpf.leilao.uitests.listagem;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.mp.mpf.leilao.uitests.login.LoginPage;

public class ListagemLeiloesAbertosSeleniumSuiteTest {

	private WebDriver driver;

	@Before
	public void criaDriver() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void fechaDriver() {
		driver.quit();
	}

	@Test
	public void aposAutenticarUsuarioDeveVerListaDeTodosLeiloesAbertos() {
		ListagemLeilaoAbertosPage listagemLeilaoAbertosPage =
			new LoginPage(driver).visita("http://localhost:8080/leilao").autentica("joaosilva@mpf.mp.br", "123456");

		Assert.assertTrue(listagemLeilaoAbertosPage.temListaDeLeiloes());

		/*driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("joaosilva@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		assertThat(driver.findElements(By.cssSelector("div.bs-callout")), not(empty()));*/
	}

}
