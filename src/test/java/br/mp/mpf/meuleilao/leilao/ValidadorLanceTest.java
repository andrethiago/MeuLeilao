package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLanceTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	@Test
	public void deveSerPossivelFazerLanceEmUmLeilaoAberto() {
		ValidadorLance validador = new ValidadorLance();

		Date daqui2Semanas = LocalDate.now().plusWeeks(2).toDate();
		Leilao leilao = new Leilao();
		leilao.setDataFim(daqui2Semanas);

		validador.validar(null, BigDecimal.valueOf(10.0), leilao);
		assertTrue("Deve ser possível fazer lances em um leilão aberto.", true);
	}

	@Test(expected = LanceInvalidoException.class)
	public void naoEPossivelFazerLanceEmLeiloesFechados() {
		ValidadorLance validador = new ValidadorLance();
		Date ontem = LocalDate.now().minusDays(1).toDate();
		Leilao leilao = new Leilao();
		leilao.setDataFim(ontem);

		validador.validar(null, BigDecimal.valueOf(10.0), leilao);
	}

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalha() {
		ValidadorLance validador = new ValidadorLance();
		Date ontem = LocalDate.now().minusDays(1).toDate();
		Leilao leilao = new Leilao();
		leilao.setDataFim(ontem);

		try {
			validador.validar(null, BigDecimal.valueOf(10.0), leilao);
			fail("Lance em leilão fechado deve lançar uma exceção.");
		} catch (LanceInvalidoException e) {
			assertEquals("Não é possível fazer lances em um leilão fechado.", e.getMessage());
		}
	}

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalhaVersaoElegante() {
		ValidadorLance validador = new ValidadorLance();
		Date ontem = LocalDate.now().minusDays(1).toDate();
		Leilao leilao = new Leilao();
		leilao.setDataFim(ontem);

		excecaoEsperada.expect(LanceInvalidoException.class);
		excecaoEsperada.expectMessage("Não é possível fazer lances em um leilão fechado.");

		validador.validar(null, BigDecimal.valueOf(10.0), leilao);
	}

}
