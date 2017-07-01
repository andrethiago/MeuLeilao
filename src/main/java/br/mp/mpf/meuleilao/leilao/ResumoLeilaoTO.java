package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoLeilaoTO {

	private Long id;
	private String nomeLeilao;
	private Integer quantidadeLances = 0;
	private BigDecimal maiorLance = BigDecimal.ZERO;
	private BigDecimal lanceMinimo;
	private Date dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getLanceMinimo() {
		return lanceMinimo;
	}

	public void setLanceMinimo(BigDecimal lanceMinimo) {
		this.lanceMinimo = lanceMinimo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResumoLeilaoTO [");
		if (nomeLeilao != null) {
			builder.append("nomeLeilao=");
			builder.append(nomeLeilao);
			builder.append(", ");
		}
		if (dataFim != null) {
			builder.append("dataFim=");
			builder.append(dataFim);
		}
		builder.append("]");
		return builder.toString();
	}

}
