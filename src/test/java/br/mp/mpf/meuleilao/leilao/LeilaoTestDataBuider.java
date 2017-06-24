package br.mp.mpf.meuleilao.leilao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.mp.mpf.meuleilao.Item;
import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;

public class LeilaoTestDataBuider {

	private Long id = Long.valueOf(1L);
	private String nome = "Leilão de Teste";
	private Item item = new Item(); // poderia ter um ItemTestDataBuilder
	private Set<Lance> lances = new HashSet<>();
	private Date dataInicio = new Date();
	private Date dataFim = LocalDate.now().plusWeeks(2).toDate();

	private LeilaoTestDataBuider() {
		// para ninguém conseguir estanciar essa classe
	}

	public static LeilaoTestDataBuider umLeilao() {
		return new LeilaoTestDataBuider();
	}

	public LeilaoTestDataBuider comId(Long id) {
		this.id = id;
		return this;
	}

	public LeilaoTestDataBuider comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public LeilaoTestDataBuider paraItem(Item item) {
		this.item = item;
		return this;
	}

	public LeilaoTestDataBuider comLances(Set<Lance> lances) {
		this.lances = lances;
		return this;
	}

	public LeilaoTestDataBuider comDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
		return this;
	}

	public LeilaoTestDataBuider comDataFim(Date dataFim) {
		this.dataFim = dataFim;
		return this;
	}

	public Leilao build() {
		Leilao leilao = new Leilao(item, dataInicio, dataFim);
		leilao.setId(id);
		leilao.setNome(nome);
		leilao.setLances(lances);

		return leilao;
	}

}
