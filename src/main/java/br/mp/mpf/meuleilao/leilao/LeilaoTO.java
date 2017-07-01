package br.mp.mpf.meuleilao.leilao;

import java.util.Date;

import br.mp.mpf.meuleilao.Item;

public class LeilaoTO {

	private Item item;

	private Date dataInicio;

	private Date dataFim;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
