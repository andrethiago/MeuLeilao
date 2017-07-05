package br.mp.mpf.leilao.uitests.login;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("joaosilva@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Assert.assertTrue(driver.findElement(By.className("navbar")) != null);
	}

	@Test
	public void usuarioNaoCadastradoNaoDeveConseguirFazerLogin() {
		driver.get("http://localhost:8080/leilao");
		driver.findElement(By.name("username")).sendKeys("andrethiago@mpf.mp.br");
		driver.findElement(By.name("password")).sendKeys("123456");

		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Assert.assertTrue(driver.getCurrentUrl().contains("?error"));
	}

}
