package br.mp.mpf.meuleilao.leilao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class OrdenadorLeiloes {

	public List<ResumoLeilaoTO> ordenarPorDataFimMaisRecente(List<ResumoLeilaoTO> tos) {
		return tos.stream().sorted(Comparator.comparing(ResumoLeilaoTO::getDataFim)).collect(Collectors.toList());
	}

}
