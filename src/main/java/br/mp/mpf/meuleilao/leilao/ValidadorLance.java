package br.mp.mpf.meuleilao.leilao;

import java.util.Set;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLance {

	private Set<ValidaLance> validadores;

	public ValidadorLance() {
		// apenas para compatibilidade
	}

	public ValidadorLance(Set<ValidaLance> validadores) {
		this.validadores = validadores;
	}

	public void validar(Lance novoLance, Leilao leilao) {

		for (ValidaLance validador : validadores) {
			validador.validar(novoLance, leilao);
		}
	}

}
