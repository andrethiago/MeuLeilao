package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class ValidadorLanceTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	private ValidadorLance validador;
	private LeilaoTestDataBuider leilaoBuilder;

	@Before
	public void preparaValidador() {
		//validador = new ValidadorLance();
		Set<ValidaLance> validadores = new HashSet<>();
		validadores.add(new ValidadorLanceLeilaoFechado());
		validadores.add(new ValidadorLanceMaiorDeTodos());
		validadores.add(new ValidadorLanceSeguidoMesmoUsuario());
		validador = new ValidadorLance(validadores);
	}

	@Before
	public void criaLeilaoAberto() {
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

	/*@Test(expected = LanceInvalidoException.class)
	public void naoEPossivelFazerLanceEmLeiloesFechados() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
	
		validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(ontem).build());
	}
	
	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalha() {
		Date ontem = LocalDate.now().minusDays(1).toDate();
	
		try {
			validador.validar(LanceTestDataBuilder.umLance().build(), leilaoBuilder.comDataFim(ontem).build());
			fail("Lance em leilão fechado deve lançar uma exceção.");
		} catch (LanceInvalidoException e) {
			assertEquals("Não é possível fazer lances em um leilão fechado.", e.getMessage());
		}
	}
	*/

	@Test
	public void lanceEmUmLeilaoFechadoDeveTerMensagemExplicativaDoMotivoDeFalhaVersaoElegante() {
		Date ontem = LocalDate.now().minusDays(1).toDate();

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

	@Test
	public void deveSerPossivelFazerUmLanceMaiorDoQueOMaiorExistente() {
		Set<Lance> lancesDoLeilao = new HashSet<>(Arrays.asList(
			new Lance[] {
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario1@email", null))
					.comValor(BigDecimal.valueOf(90.0))
					.build(),
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario2@email", null))
					.comValor(BigDecimal.valueOf(95.0))
					.build(),
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario3@email", null))
					.comValor(BigDecimal.valueOf(100.0))
					.build()}));
		Lance novoLance = LanceTestDataBuilder.umLance().comValor(BigDecimal.valueOf(110.0)).build();
		Leilao leilao = leilaoBuilder.comDataFim(null).comLances(lancesDoLeilao).build();

		validador.validar(novoLance, leilao);
	}

	@Test
	public void naoDeveSerPossivelFazerUmLanceMenorDoQueOMaiorExistente() {
		Set<Lance> lancesDoLeilao = new HashSet<>(Arrays.asList(
			new Lance[] {
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario1@email", null))
					.comValor(BigDecimal.valueOf(90.0))
					.build(),
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario2@email", null))
					.comValor(BigDecimal.valueOf(95.0))
					.build(),
				LanceTestDataBuilder.umLance()
					.comOfertante(new Usuario(null, "usuario3@email", null))
					.comValor(BigDecimal.valueOf(100.0))
					.build()}));
		Lance novoLance = LanceTestDataBuilder.umLance().comValor(BigDecimal.valueOf(99.0)).build();
		Leilao leilao = leilaoBuilder.comDataFim(null).comLances(lancesDoLeilao).build();

		excecaoEsperada.expect(LanceInvalidoException.class);
		excecaoEsperada.expectMessage("Não é possível dar um lance menor do que o maior já existente.");

		validador.validar(novoLance, leilao);
	}

	@Test
	public void naoDeveSerPossivelOMesmoUsuarioFazerDoisLancesSeguidosEmUmLeilao() {
		Lance primeiroLance = LanceTestDataBuilder.umLance()
			.comData(LocalDate.now().minusDays(2).toDate())
			.comOfertante(new Usuario(null, "usuario1@email", null))
			.comValor(BigDecimal.valueOf(90.0))
			.build();

		Lance segundoLance = LanceTestDataBuilder.umLance()
			.comData(LocalDate.now().minusDays(1).toDate())
			.comOfertante(new Usuario(null, "usuario2@email", null))
			.comValor(BigDecimal.valueOf(100.0))
			.build();

		Lance novoLance = LanceTestDataBuilder.umLance()
			.comData(LocalDate.now().minusDays(1).toDate())
			.comOfertante(new Usuario(null, "usuario2@email", null))
			.comValor(BigDecimal.valueOf(105.0))
			.build();

		Set<Lance> lancesDoLeilao = new HashSet<>(Arrays.asList(new Lance[] {primeiroLance, segundoLance}));
		Leilao leilao = leilaoBuilder.comDataFim(null).comLances(lancesDoLeilao).build();

		excecaoEsperada.expect(LanceInvalidoException.class);
		excecaoEsperada.expectMessage("Não é possível o mesmo usuário dar dois lances seguidos no leilão.");

		validador.validar(novoLance, leilao);
	}

}
