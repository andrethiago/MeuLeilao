package br.mp.mpf.meuleilao.leilao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class OrdenadorLeiloes {

	public List<ResumoLeilaoTO> ordenarPorDataFimMaisRecente(List<ResumoLeilaoTO> tos) {
		/*Collections.sort(tos, new Comparator<ResumoLeilaoTO>() {
		
			@Override
			public int compare(ResumoLeilaoTO resumo1, ResumoLeilaoTO resumo2) {
				if (resumo1.getDataFim() == null) {
					return 1;
				}
				return resumo1.getDataFim().compareTo(resumo2.getDataFim());
			}
		});
		
		return tos;*/

		return tos.stream()
			.sorted(Comparator.comparing(ResumoLeilaoTO::getDataFim, Comparator.nullsLast(Comparator.naturalOrder())))
			.collect(Collectors.toList());
	}

}
