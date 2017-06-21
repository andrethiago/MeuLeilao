package br.mp.mpf.meuleilao;

import java.math.BigDecimal;

public class Lance {

	private Long id;

	private Usuario ofertante;

	private BigDecimal valor;

	private Leilao leilao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getOfertante() {
		return ofertante;
	}

	public void setOfertante(Usuario ofertante) {
		this.ofertante = ofertante;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leilao == null) ? 0 : leilao.hashCode());
		result = prime * result + ((ofertante == null) ? 0 : ofertante.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Lance other = (Lance) obj;
		if (leilao == null) {
			if (other.leilao != null) {
				return false;
			}
		} else if (!leilao.equals(other.leilao)) {
			return false;
		}
		if (ofertante == null) {
			if (other.ofertante != null) {
				return false;
			}
		} else if (!ofertante.equals(other.ofertante)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lance [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (ofertante != null) {
			builder.append("ofertante=");
			builder.append(ofertante);
			builder.append(", ");
		}
		if (valor != null) {
			builder.append("valor=");
			builder.append(valor);
			builder.append(", ");
		}
		if (leilao != null) {
			builder.append("leilao=");
			builder.append(leilao);
		}
		builder.append("]");
		return builder.toString();
	}

}
