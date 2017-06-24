package br.mp.mpf.meuleilao.leilao;

import org.joda.time.LocalDate;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class ValidadorLance {

	public void validar(Lance lance, Leilao leilao) {
		if (leilao.getDataFim() != null) {

			LocalDate dataFim = LocalDate.fromDateFields(leilao.getDataFim());
			LocalDate dataLance = LocalDate.fromDateFields(lance.getData());

			if (dataLance.isAfter(dataFim)) {
				throw new LanceInvalidoException("Não é possível fazer lances em um leilão fechado.");
			}
		}

	}

}
