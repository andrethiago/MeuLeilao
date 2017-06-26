package br.mp.mpf.meuleilao.leilao;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLanceSeguidoMesmoUsuario implements ValidaLance {

	@Override
	public void validar(Lance novoLance, Leilao leilao) {
		Set<Lance> lancesDoLeilao = leilao.getLances();

		if (CollectionUtils.isNotEmpty(lancesDoLeilao)) {
			Lance lanceMaisRecente = lancesDoLeilao.stream()
				.sorted(Comparator.comparing(Lance::getData).reversed())
				.collect(Collectors.toList())
				.get(0);

			if (novoLance.getOfertante().equals(lanceMaisRecente.getOfertante())) {
				throw new LanceInvalidoException("Não é possível o mesmo usuário dar dois lances seguidos no leilão.");
			}
		}
	}

}
