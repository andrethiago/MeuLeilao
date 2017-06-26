package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class ValidadorLanceSeguidoMesmoUsuarioTest {

	@Rule
	public ExpectedException excecaoEsperada = ExpectedException.none(); // por padrão não são lançadas exceções

	private ValidadorLanceSeguidoMesmoUsuario validador;
	private LeilaoTestDataBuider leilaoBuilder;

	@Before
	public void prepara() {
		validador = new ValidadorLanceSeguidoMesmoUsuario();
		leilaoBuilder = LeilaoTestDataBuider.umLeilao();
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
