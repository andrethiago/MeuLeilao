package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLanceTest {

	@Test
	public void deveSerPossivelFazerLanceEmUmLeilaoAberto() {
		ValidadorLance validador = new ValidadorLance();

		Date daqui2Semanas = LocalDate.now().plusWeeks(2).toDate();
		Leilao leilao = new Leilao();
		leilao.setDataFim(daqui2Semanas);

		validador.validar(null, BigDecimal.valueOf(10.0), leilao);
		Assert.assertTrue("Deve ser possível fazer lances em um leilão aberto.", true);
	}

}
