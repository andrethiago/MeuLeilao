package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoLeilaoTO {

	private String nomeLeilao;
	private Integer quantidadeLances = 0;
	private BigDecimal maiorLance = BigDecimal.ZERO;
	private Date dataFim;

	public String getNomeLeilao() {
		return nomeLeilao;
	}

	public void setNomeLeilao(String nomeLeilao) {
		this.nomeLeilao = nomeLeilao;
	}

	public Integer getQuantidadeLances() {
		return quantidadeLances;
	}

	public void setQuantidadeLances(Integer quantidadeLances) {
		this.quantidadeLances = quantidadeLances;
	}

	public BigDecimal getMaiorLance() {
		return maiorLance;
	}

	public void setMaiorLance(BigDecimal maiorLance) {
		this.maiorLance = maiorLance;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
