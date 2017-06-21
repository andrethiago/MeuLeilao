package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

@Service
public class RegistradorLanceService {

	private LanceRepository repository;

	protected RegistradorLanceService(LanceRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Lance fazLance(Usuario ofertante, BigDecimal valor, Leilao leilao) {
		Lance lance = new Lance(ofertante, valor, leilao);
		repository.incluir(lance);

		return lance;
	}

}
