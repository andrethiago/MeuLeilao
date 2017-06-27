package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class VisualizadorResumoLeiloesService {

	private LeilaoRepository repository;

	public List<ResumoLeilaoTO> visualizaTodosLeiloesAbertos() {
		List<Leilao> leiloes = repository.consultarTodosLeiloesAbertos();

		List<ResumoLeilaoTO> tos = new ArrayList<>();
		for (Leilao leilao : leiloes) {
			ResumoLeilaoTO to = new ResumoLeilaoTO();
			to.setNomeLeilao(leilao.getNome());
			to.setQuantidadeLances(leilao.getLances().size());
			to.setMaiorLance(getMaiorLance(leilao));

			tos.add(to);
		}

		return tos;
	}

	private BigDecimal getMaiorLance(Leilao leilao) {
		/*BigDecimal maiorLance = BigDecimal.ZERO;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor().compareTo(maiorLance) >= 0) {
				maiorLance = lance.getValor();
			}
		}
		return maiorLance;*/

		Lance maiorLance = leilao.getLances().stream().max((l1, l2) -> l1.getValor().compareTo(l2.getValor())).get();
		return maiorLance.getValor();

	}

}
