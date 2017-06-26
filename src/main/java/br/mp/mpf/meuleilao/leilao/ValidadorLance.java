package br.mp.mpf.meuleilao.leilao;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;

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

		//validarSeLeilaoEstaFechado(novoLance, leilao);
		//validarSeNovoLanceMaiorDeTodos(novoLance, leilao);
		//validarLanceSeguidoDoMesmoUsuario(novoLance, leilao);
	}

	private void validarSeLeilaoEstaFechado(Lance novoLance, Leilao leilao) {
		if (leilao.getDataFim() != null) {
			LocalDate dataFim = LocalDate.fromDateFields(leilao.getDataFim());
			LocalDate dataLance = LocalDate.fromDateFields(novoLance.getData());

			if (dataLance.isAfter(dataFim)) {
				throw new LanceInvalidoException("Não é possível fazer lances em um leilão fechado.");
			}
		}
	}

	private void validarSeNovoLanceMaiorDeTodos(Lance novoLance, Leilao leilao) {
		Set<Lance> lancesDoLeilao = leilao.getLances();
		for (Lance lance : lancesDoLeilao) {
			if (lance.getValor().compareTo(novoLance.getValor()) == 1) {
				throw new LanceInvalidoException("Não é possível dar um lance menor do que o maior já existente.");
			}
		}

	}

	private void validarLanceSeguidoDoMesmoUsuario(Lance novoLance, Leilao leilao) {
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
