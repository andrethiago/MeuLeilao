package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;

public class ResumoLeilaoTO {

	private String nomeLeilao;
	private Integer quantidadeLances;
	private BigDecimal maiorLance;

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

}
