package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidadorLanceTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	private ValidadorLance validador;
	//private Leilao leilao;
	private LeilaoTestDataBuider leilaoBuilder;

	@Before
	public void preparaValidador() {
		validador = new ValidadorLance();
	}

	@Before
	public void criaLeilaoAberto() {
		//Date daqui2Semanas = LocalDate.now().plusWeeks(2).toDate();
		//leilao = new Leilao();
		//leilao.setDataFim(daqui2Semanas);
		leilaoBuilder = LeilaoTestDataBuider.umLeilao();
	}

	@Test
	public void deveSerPossivelFazerLanceEmUmLeilaoAberto() {
		validador.validar(null, BigDecimal.valueOf(10.0), leilaoBuilder.build());
		assertTrue("Deve ser possível fazer lances em um leilão aberto.", true);
	}

	@Test(expected = LanceInvalidoException.class)
	public void naoEPossivelFazerLanceEmLeiloesFechados() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
		//leilao.setDataFim(ontem);

		validador.validar(null, BigDecimal.valueOf(10.0), leilaoBuilder.comDataFim(ontem).build());
	}

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalha() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
		//leilao.setDataFim(ontem);

		try {
			validador.validar(null, BigDecimal.valueOf(10.0), leilaoBuilder.comDataFim(ontem).build());
			fail("Lance em leilão fechado deve lançar uma exceção.");
		} catch (LanceInvalidoException e) {
			assertEquals("Não é possível fazer lances em um leilão fechado.", e.getMessage());
		}
	}

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalhaVersaoElegante() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
		//leilao.setDataFim(ontem);

		excecaoEsperada.expect(LanceInvalidoException.class);
		excecaoEsperada.expectMessage("Não é possível fazer lances em um leilão fechado.");

		validador.validar(null, BigDecimal.valueOf(10.0), leilaoBuilder.comDataFim(ontem).build());
	}

	/*@BeforeClass
	public static void setupGlobal() {
		System.out.println("@BeforeClass antes de todos os testes.");
	}
	
	@AfterClass
	public static void cleanUpGlobal() {
		System.out.println("@AfterClass depois de todos os testes.");
	}
	
	@Before
	public void setup() {
		System.out.println("@Before antes de cada teste.");
	}
	
	@After
	public void cleanUp() {
		System.out.println("@After depois de cada teste.");
	}*/

}
