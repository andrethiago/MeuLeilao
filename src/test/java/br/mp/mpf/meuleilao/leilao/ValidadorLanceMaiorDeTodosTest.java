package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class ValidadorLanceMaiorDeTodosTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	private ValidadorLanceMaiorDeTodos validador;
	private LeilaoTestDataBuider leilaoBuilder;

	@Before
	public void prepara() {
		validador = new ValidadorLanceMaiorDeTodos();
		leilaoBuilder = LeilaoTestDataBuider.umLeilao();
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

}
