package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoLeilaoTOTestDataBuilder {

	private String nomeLeilao = "Nome do Leilão";
	private Integer quantidadeLances = 0;
	private BigDecimal maiorLance = BigDecimal.ZERO;
	private Date dataFim = new Date();

	private ResumoLeilaoTOTestDataBuilder() {
		//
	}

	public static ResumoLeilaoTOTestDataBuilder umResumoLeilao() {
		return new ResumoLeilaoTOTestDataBuilder();
	}

	public ResumoLeilaoTOTestDataBuilder comNomeLeilao(String nomeLeilao) {
		this.nomeLeilao = nomeLeilao;
		return this;
	}

	public ResumoLeilaoTOTestDataBuilder comQuantidadeLances(Integer quantidadeLances) {
		this.quantidadeLances = quantidadeLances;
		return this;
	}

	public ResumoLeilaoTOTestDataBuilder comMaiorLance(BigDecimal maiorLance) {
		this.maiorLance = maiorLance;
		return this;
	}

	public ResumoLeilaoTOTestDataBuilder comDataFim(Date dataFim) {
		this.dataFim = dataFim;
		return this;
	}

	public ResumoLeilaoTO build() {
		ResumoLeilaoTO to = new ResumoLeilaoTO();
		to.setNomeLeilao(nomeLeilao);
		to.setDataFim(dataFim);
		to.setMaiorLance(maiorLance);
		to.setQuantidadeLances(quantidadeLances);

		return to;
	}

}
