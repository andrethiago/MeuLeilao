package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLanceLeilaoFechadoTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	private ValidadorLanceLeilaoFechado validador;
	private LeilaoTestDataBuider leilaoBuilder;

	@Before
	public void prepara() {
		validador = new ValidadorLanceLeilaoFechado();
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

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalhaVersaoElegante() {
		Date ontem = LocalDate.now().minusDays(1).toDate();

		excecaoEsperada.expect(LanceInvalidoException.class);
		excecaoEsperada.expectMessage(MensagemValidacao.NAO_DEVE_SER_POSSIVEL_FAZER_LANCES_LEILAO_FECHADO);

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

}
