package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

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
			if (CollectionUtils.isNotEmpty(leilao.getLances())) {
				to.setQuantidadeLances(leilao.getLances().size());
				to.setMaiorLance(getMaiorLance(leilao));
			}
			to.setDataFim(leilao.getDataFim());
			tos.add(to);
		}

		return ordenarPorDataFimMaisRecente(tos);
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

	private List<ResumoLeilaoTO> ordenarPorDataFimMaisRecente(List<ResumoLeilaoTO> tos) {
		/*Collections.sort(tos, new Comparator<ResumoLeilaoTO>() {
		
			@Override
			public int compare(ResumoLeilaoTO resumo1, ResumoLeilaoTO resumo2) {
				return resumo1.getDataFim().compareTo(resumo2.getDataFim());
			}
		});*/

		return tos.stream().sorted(Comparator.comparing(ResumoLeilaoTO::getDataFim)).collect(Collectors.toList());
	}

}
