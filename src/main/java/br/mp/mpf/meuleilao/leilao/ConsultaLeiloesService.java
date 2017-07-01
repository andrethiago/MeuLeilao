package br.mp.mpf.meuleilao.leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mp.mpf.meuleilao.Leilao;

@Service
public class ConsultaLeiloesService {

	@Autowired
	private LeilaoRepository repository;

	public Leilao consultarPorId(Long id) {
		return repository.consultarPorId(id);
	}

}
