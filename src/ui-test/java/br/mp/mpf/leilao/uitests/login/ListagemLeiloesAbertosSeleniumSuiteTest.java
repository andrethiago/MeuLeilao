package br.mp.mpf.leilao.uitests.login;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ListagemLeiloesAbertosSeleniumSuiteTest {

	private WebDriver driver;

	@Before
	public void criaDriver() {
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void fechaDriver() {
		driver.quit();
	}

	@Test
	public void aposAutenticarUsuarioDeveVerListaDeTodosLeiloesAbertos() {
		driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("joaosilva@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		assertThat(driver.findElements(By.cssSelector("div.bs-callout")), not(empty()));
	}

}
