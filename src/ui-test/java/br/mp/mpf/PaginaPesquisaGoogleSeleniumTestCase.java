package br.mp.mpf;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaPesquisaGoogleSeleniumTestCase {

	private WebDriver driver;

	@After
	public void fechaBrowser() {
		driver.quit();
	}

	//@Test
	public void pesquisaGooglePorCodeATest() /*throws InterruptedException*/ {
		// cria o driver e visita a página de pesquisa do Google
		driver = new ChromeDriver();
		driver.get("http://www.google.com.br");

		// digite o termo 'code a test' na caixa de pesquisa
		driver.findElement(By.name("q")).sendKeys("testes de aceitação");

		//Thread.sleep(10000);
		// verifica que resultados foram encontrados
		Assert.assertNotNull(driver.findElement(By.cssSelector("div.srg")));

	}

	//@Test
	public void pesquisaGooglePorCodeATestExplicitWait() {
		// cria o driver e visita a página de pesquisa do Google
		driver = new ChromeDriver();
		driver.get("http://www.google.com.br");

		// digite o termo 'code a test' na caixa de pesquisa
		driver.findElement(By.name("q")).sendKeys("testes de aceitação");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> divResultados =
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.srg")));

		// verifica que resultados foram encontrados
		Assert.assertNotNull(divResultados);

	}

	@Test
	public void pesquisaGooglePorCodeATestImplicitWait() {
		// cria o driver e visita a página de pesquisa do Google
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://www.google.com.br");

		// digite o termo 'code a test' na caixa de pesquisa
		driver.findElement(By.name("q")).sendKeys("testes de aceitação");

		// verifica que resultados foram encontrados
		Assert.assertNotNull(driver.findElement(By.cssSelector("div.srg")));

	}

}
