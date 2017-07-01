package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;

public class LanceTO {

	private Long usuario;
	private BigDecimal valor;
	private Long leilao;

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getLeilao() {
		return leilao;
	}

	public void setLeilao(Long leilao) {
		this.leilao = leilao;
	}

}
