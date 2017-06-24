package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class ValidadorLance {

	public void validar(Usuario ofertante, BigDecimal valor, Leilao leilao) {
		if (leilao.getDataFim() != null) {
			Date agora = new Date();

			if (agora.after(leilao.getDataFim())) {
				throw new LanceInvalidoException("Não é possível fazer lances em um leilão fechado.");
			}
		}

	}

}
