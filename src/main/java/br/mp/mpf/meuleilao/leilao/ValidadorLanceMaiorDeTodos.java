package br.mp.mpf.meuleilao.leilao;

import java.util.Set;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLanceMaiorDeTodos implements ValidaLance {

	@Override
	public void validar(Lance novoLance, Leilao leilao) {
		Set<Lance> lancesDoLeilao = leilao.getLances();
		for (Lance lance : lancesDoLeilao) {
			if (lance.getValor().compareTo(novoLance.getValor()) == 1) {
				throw new LanceInvalidoException("Não é possível dar um lance menor do que o maior já existente.");
			}
		}
	}

}
