package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Leilao;

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
		Date daqui2Semanas = LocalDate.now().plusWeeks(2).toDate();
		validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(daqui2Semanas).build());
		assertTrue("Deve ser possível fazer lances em um leilão aberto.", true);
	}

	@Test
	public void deveSerPossivelFazerLanceEmUmLeilaoAbertoSemDataFim() {
		validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(null).build());
		assertTrue("Deve ser possível fazer lances em um leilão aberto sem data fim.", true);
	}

	@Test(expected = LanceInvalidoException.class)
	public void naoEPossivelFazerLanceEmLeiloesFechados() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
		//leilao.setDataFim(ontem);

		validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(ontem).build());
	}

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalha() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
		//leilao.setDataFim(ontem);

		try {
			validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(ontem).build());
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

		validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(ontem).build());
	}

	@Test
	public void deveSerPossivelFazerLanceNoUltimoMomentoDoLeilao() throws InterruptedException {
		Date dataLance = new LocalDateTime(2017, 7, 3, 23, 59, 59).toDate();
		Date dataFimLeilao = new LocalDateTime(2017, 7, 3, 23, 59, 59).toDate();

		Leilao leilaoAcabaHoje = leilaoBuilder.comDataFim(dataFimLeilao).build();

		Thread.sleep(1000L);

		validador.validar(LanceTestDataBuilder.umLance().comData(dataLance).build(), leilaoAcabaHoje);
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
