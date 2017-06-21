package br.mp.mpf.meuleilao.leilao;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.mp.mpf.meuleilao.Item;
import br.mp.mpf.meuleilao.Leilao;

@Service
public class CriadorLeilaoService {

	private LeilaoRepository repository;

	public void criar(Item item) {
		Leilao leilao = new Leilao();
		leilao.setDataInicio(new Date());
		leilao.setItem(item);
		leilao.setNome(item.getNome());

		repository.incluir(leilao);
	}

}
