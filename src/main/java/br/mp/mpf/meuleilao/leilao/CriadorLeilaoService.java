package br.mp.mpf.meuleilao.leilao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mp.mpf.meuleilao.Item;
import br.mp.mpf.meuleilao.Leilao;

@Service
public class CriadorLeilaoService {

	@Autowired
	private LeilaoRepository repository;

	@Transactional
	public void criar(Item item, Date dataInicio, Date dataFim) {
		Leilao leilao = new Leilao();
		leilao.setDataInicio(dataInicio);
		leilao.setDataFim(dataFim);;
		leilao.setItem(item);
		leilao.setNome(item.getNome());

		repository.incluir(leilao);
	}

}
