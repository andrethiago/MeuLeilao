package br.mp.mpf;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformativoDeTesesSeleniumTestCase {

	@Before
	public void defineCaminhoFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "/home/andrethiago/workspace/MeuLeilao/geckodriver");
	}

	// Chrome: System.setProperty("webdriver.chrome.driver", "caminho-do-chromedriver");
	// Firefox: System.setProperty("webdriver.gecko.driver", "caminho-do-firefoxdriver");

	@Test
	public void pesquisaPorTermoInconstitucionalDeveRetornarResultados() {

		// para o FirefoxDriver: The latest version can be downloaded from https://github.com/mozilla/geckodriver/releases
		WebDriver driver = new ChromeDriver(); // pode ser FirefoxDriver, HtmlUnitDriver...
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Visita a página de Informativo de Teses
		driver.get("http://www.mpf.mp.br/pgr/institucional/procurador-geral-da-republica/informativo-de-teses");
		// Encontra a caixa de texto na página e digita o termo inconstitucional
		WebElement campoBusca = driver.findElement(By.name("campobusca"));
		campoBusca.sendKeys("inconstitucional");
		// Clica no botão "Consultar"
		WebElement botaoConsultar = driver.findElement(By.cssSelector("input[name='Submit']")); // #campo_busca > label > input[type="submit"]
		botaoConsultar.click();
		// Verificar o resultado (tornado essa execução um verdadeiro teste!)
		Assert
			.assertThat(driver.findElement(By.cssSelector("div.infTese_resultBusca_titulo")), Matchers.notNullValue());
		// fecha o browser
		driver.quit();

	}

}
