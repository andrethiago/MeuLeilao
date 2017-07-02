package br.mp.mpf.meuleilao.leilao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;
import br.mp.mpf.meuleilao.infra.BaseRepository;

@Repository
class LeilaoRepository extends BaseRepository<Leilao> {

	@SuppressWarnings("unchecked")
	public List<Leilao> consultarTodosLeiloesAbertos() {
		return getSession()
			.createQuery(
				"from Leilao where dataInicio <= current_timestamp and (dataFim is null or dataFim > current_date)")
			.list();
	}

	@SuppressWarnings("unchecked")
	public List<Leilao> consultarTodosLeiloes(Usuario usuario) {
		return getSession().createQuery("from Leilao where item.dono = :usuario")
			.setParameter("usuario", usuario)
			.list();
	}

}
