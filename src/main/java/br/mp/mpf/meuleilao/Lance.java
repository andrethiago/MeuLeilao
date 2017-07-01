package br.mp.mpf.meuleilao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LANCE")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "SEQ_LANCE", allocationSize = 1)
public class Lance {

	@Id
	@Column(name = "ID_LANCE", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFERTANTE", nullable = false)
	private Usuario ofertante;

	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LEILAO", nullable = false)
	private Leilao leilao;

	@Column(name = "DATA_LANCE", nullable = false)
	private Date data;

	public Lance() {}

	public Lance(Usuario ofertante, BigDecimal valor, Leilao leilao) {
		this.ofertante = ofertante;
		this.valor = valor;
		this.leilao = leilao;
		this.data = new Date();
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date hora) {
		this.data = hora;
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
