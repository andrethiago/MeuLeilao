package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

@RunWith(MockitoJUnitRunner.class)
public class VisualizadorResumoLeiloesServiceTest {

	@Mock
	private LeilaoRepository repository;

	@InjectMocks
	private VisualizadorResumoLeiloesService service;

	private List<Leilao> leiloes;

	private List<Lance> lancesGarmin;
	private List<Lance> lancesIphone4;
	private List<Lance> lancesGeladeira;
	private Leilao leilaoGarmin;
	private Leilao leilaoIphone;
	private Leilao leilaoGeladeira;

	@Before
	public void setup() {
		lancesGarmin = Arrays.asList(
			new Lance[] {LanceTestDataBuilder.umLance().comId(1L).comValor(BigDecimal.valueOf(550.0)).build(),
				LanceTestDataBuilder.umLance().comId(2L).comValor(BigDecimal.valueOf(450.0)).build(),
				LanceTestDataBuilder.umLance().comId(3L).comValor(BigDecimal.valueOf(600.0)).build()});

		lancesIphone4 = Arrays.asList(
			new Lance[] {LanceTestDataBuilder.umLance().comId(4L).comValor(BigDecimal.valueOf(1200.0)).build(),
				LanceTestDataBuilder.umLance().comId(5L).comValor(BigDecimal.valueOf(1000.0)).build(),
				LanceTestDataBuilder.umLance().comId(6L).comValor(BigDecimal.valueOf(1120.0)).build(),
				LanceTestDataBuilder.umLance().comId(7L).comValor(BigDecimal.valueOf(1075.0)).build()});

		lancesGeladeira = Arrays
			.asList(new Lance[] {LanceTestDataBuilder.umLance().comId(8L).comValor(BigDecimal.valueOf(750.0)).build()});

		leilaoGarmin = LeilaoTestDataBuider.umLeilao()
			.comDataFim(null)
			.comNome("Leilão GPS Garmin")
			.comLances(new HashSet<>(lancesGarmin))
			.build();

		leilaoIphone = LeilaoTestDataBuider.umLeilao()
			.comDataFim(null)
			.comNome("Leilão Iphone 4")
			.comLances(new HashSet<>(lancesIphone4))
			.build();

		leilaoGeladeira = LeilaoTestDataBuider.umLeilao()
			.comDataFim(null)
			.comNome("Leilão Geladeira")
			.comLances(new HashSet<>(lancesGeladeira))
			.build();

		leiloes = new ArrayList<>();
		leiloes.add(leilaoGarmin);
		leiloes.add(leilaoIphone);
		leiloes.add(leilaoGeladeira);
	}

	@Test
	public void deveSerPossivelListarParaOsLeiloesAbertosQuantidadeTotalDeLances() {
		when(repository.consultarTodosLeiloesAbertos()).thenReturn(leiloes);

		List<ResumoLeilaoTO> leiloesTO = service.visualizaTodosLeiloesAbertos();

		assertNotNull(leiloesTO);
		assertEquals(leiloes.size(), leiloesTO.size());

		for (ResumoLeilaoTO to : leiloesTO) {
			if (to.getNomeLeilao().equals(leilaoGarmin.getNome())) {
				assertEquals(leilaoGarmin.getLances().size(), to.getQuantidadeLances().intValue());
			}
			if (to.getNomeLeilao().equals(leilaoIphone.getNome())) {
				assertEquals(leilaoIphone.getLances().size(), to.getQuantidadeLances().intValue());
			}
			if (to.getNomeLeilao().equals(leilaoGeladeira.getNome())) {
				assertEquals(leilaoGeladeira.getLances().size(), to.getQuantidadeLances().intValue());
			}
		}

	}

	@Test
	public void deveSerPossivelParaOsLeiloesAbertosVisualizarMaiorLanceDeCadaUm() {
		when(repository.consultarTodosLeiloesAbertos()).thenReturn(leiloes);

		List<ResumoLeilaoTO> leiloesTO = service.visualizaTodosLeiloesAbertos();

		for (ResumoLeilaoTO to : leiloesTO) {
			if (to.getNomeLeilao().equals(leilaoGarmin.getNome())) {
				assertEquals(BigDecimal.valueOf(600.0), to.getMaiorLance());
			}
			if (to.getNomeLeilao().equals(leilaoIphone.getNome())) {
				assertEquals(BigDecimal.valueOf(1200.0), to.getMaiorLance());
			}
			if (to.getNomeLeilao().equals(leilaoGeladeira.getNome())) {
				assertEquals(BigDecimal.valueOf(750.0), to.getMaiorLance());
			}
		}
	}

}
