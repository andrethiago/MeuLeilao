package br.mp.mpf.leilao.uitests.listagem;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ListagemLeilaoAbertosPage {

	private WebDriver driver;

	public ListagemLeilaoAbertosPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isValida() {
		try {
			return driver.findElement(By.className("navbar")) != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean temListaDeLeiloes() {
		return CollectionUtils.isNotEmpty(driver.findElements(By.cssSelector("div.bs-callout")));
	}

}
