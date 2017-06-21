package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

@Service
public class RegistradorLanceService {

	@Autowired
	private LanceRepository repository;

	@Transactional
	public void fazLance(Usuario ofertante, BigDecimal valor, Leilao leilao) {
		Lance lance = new Lance(ofertante, valor, leilao);
		repository.incluir(lance);
	}

}
