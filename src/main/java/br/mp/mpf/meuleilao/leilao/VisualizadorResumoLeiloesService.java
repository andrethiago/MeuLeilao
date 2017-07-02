package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

@Service
public class VisualizadorResumoLeiloesService {

	@Autowired
	private LeilaoRepository repository;

	public List<ResumoLeilaoTO> visualizaTodosLeiloesAbertos() {
		List<Leilao> leiloes = repository.consultarTodosLeiloesAbertos();

		return getResumoLeilaoTO(leiloes);
	}

	public List<ResumoLeilaoTO> visualizaTodosLeiloes(Usuario usuario) {
		List<Leilao> leiloes = repository.consultarTodosLeiloes(usuario);

		return getResumoLeilaoTO(leiloes);
	}

	private List<ResumoLeilaoTO> getResumoLeilaoTO(List<Leilao> leiloes) {
		List<ResumoLeilaoTO> tos = new ArrayList<>();
		for (Leilao leilao : leiloes) {
			ResumoLeilaoTO to = new ResumoLeilaoTO();
			to.setId(leilao.getId());
			to.setNomeLeilao(leilao.getNome());
			to.setDono(leilao.getItem().getDono().getId());
			to.setLanceMinimo(leilao.getItem().getValorMinimo());
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
		Lance maiorLance = leilao.getLances().stream().max((l1, l2) -> l1.getValor().compareTo(l2.getValor())).get();
		return maiorLance.getValor();

	}

	private List<ResumoLeilaoTO> ordenarPorDataFimMaisRecente(List<ResumoLeilaoTO> tos) {
		return new OrdenadorLeiloes().ordenarPorDataFimMaisRecente(tos);
	}

}
