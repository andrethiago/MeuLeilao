package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

@Service
public class RegistradorLanceService {

	private LanceRepository repository;

	ValidadorLance validador = new ValidadorLance(new HashSet<>(Arrays.asList(
		new ValidaLance[] {new ValidadorLanceLeilaoFechado(), new ValidadorLanceMaiorDeTodos(),
			new ValidadorLanceSeguidoMesmoUsuario()})));

	protected RegistradorLanceService(LanceRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Lance fazLance(Usuario ofertante, BigDecimal valor, Leilao leilao) {
		Lance lance = new Lance(ofertante, valor, leilao);
		//new ValidadorLance().validar(lance, leilao);
		validador.validar(lance, leilao);
		repository.incluir(lance);

		return lance;
	}

}
