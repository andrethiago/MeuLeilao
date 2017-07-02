package br.mp.mpf.meuleilao.leilao;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class OrdenadorLeiloesTest {

	private List<ResumoLeilaoTO> tos;

	private ResumoLeilaoTO leilaoIphone, leilaoGeladeira, leilaoGarmin;

	@Before
	public void setup() {
		leilaoIphone = ResumoLeilaoTOTestDataBuilder.umResumoLeilao()
			.comNomeLeilao("Iphone")
			.comDataFim(LocalDate.now().plusWeeks(3).toDate())
			.build();
		leilaoGeladeira = ResumoLeilaoTOTestDataBuilder.umResumoLeilao()
			.comNomeLeilao("Geladeira")
			.comDataFim(LocalDate.now().plusWeeks(2).toDate())
			.build();
		leilaoGarmin = ResumoLeilaoTOTestDataBuilder.umResumoLeilao()
			.comNomeLeilao("Garmin")
			.comDataFim(LocalDate.now().plusWeeks(1).toDate())
			.build();

		tos = new ArrayList<>();
		tos.add(leilaoIphone);
		tos.add(leilaoGeladeira);
		tos.add(leilaoGarmin);

	}

	@Test
	public void verificaOrdenacaoLeilaoSemHamcrest() {
		OrdenadorLeiloes ordenador = new OrdenadorLeiloes();
		List<ResumoLeilaoTO> tosOrdenados = ordenador.ordenarPorDataFimMaisRecente(tos);

		assertEquals(leilaoGarmin, tosOrdenados.get(0));
		assertEquals(leilaoGeladeira, tosOrdenados.get(1));
		assertEquals(leilaoIphone, tosOrdenados.get(2));
	}

	@Test
	public void verificaOrdenacaoLeilaoComHamcrest() {
		OrdenadorLeiloes ordenador = new OrdenadorLeiloes();
		List<ResumoLeilaoTO> tosOrdenados = ordenador.ordenarPorDataFimMaisRecente(tos);

		assertThat(tosOrdenados, contains(leilaoGarmin, leilaoGeladeira, leilaoIphone));
	}

	@Test
	public void verificaOrdenacaoLeilaoComHamcrestDataFimNula() {
		ResumoLeilaoTO leilaoSemDataFim = ResumoLeilaoTOTestDataBuilder.umResumoLeilao()
			.comNomeLeilao("Leilão sem data fim")
			.comDataFim(null)
			.build();
		tos.add(leilaoSemDataFim);
		OrdenadorLeiloes ordenador = new OrdenadorLeiloes();
		List<ResumoLeilaoTO> tosOrdenados = ordenador.ordenarPorDataFimMaisRecente(tos);

		assertThat(tosOrdenados, contains(leilaoGarmin, leilaoGeladeira, leilaoIphone, leilaoSemDataFim));
	}

}
