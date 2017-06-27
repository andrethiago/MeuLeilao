package br.mp.mpf.meuleilao.leilao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.infra.BaseRepository;

@Repository
class LeilaoRepository extends BaseRepository<Leilao> {

	public List<Leilao> consultarTodosLeiloesAbertos() {
		return getSession().createQuery("from Leilao where dataFim is null or dataFim < sysdate").list();
	}

}
