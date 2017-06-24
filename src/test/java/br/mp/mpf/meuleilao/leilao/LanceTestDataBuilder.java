package br.mp.mpf.meuleilao.leilao;

import java.math.BigDecimal;
import java.util.Date;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class LanceTestDataBuilder {

	private Long id = Long.valueOf(1L);
	private Usuario ofertante = new Usuario("Nome", "email@email.com", "123456");
	private BigDecimal valor = BigDecimal.valueOf(10.0);
	private Leilao leilao = LeilaoTestDataBuider.umLeilao().build();
	private Date data = new Date();

	private LanceTestDataBuilder() {
		// não deve ser possível instanciar esse builder diretamente
	}

	public static LanceTestDataBuilder umLance() {
		return new LanceTestDataBuilder();
	}

	public LanceTestDataBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public LanceTestDataBuilder comOfertante(Usuario ofertante) {
		this.ofertante = ofertante;
		return this;
	}

	public LanceTestDataBuilder comValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}

	public LanceTestDataBuilder paraLeilao(Leilao leilao) {
		this.leilao = leilao;
		return this;
	}

	public LanceTestDataBuilder comData(Date dataLance) {
		this.data = dataLance;
		return this;
	}

	public Lance build() {
		Lance lance = new Lance(ofertante, valor, leilao);
		lance.setId(id);
		lance.setData(data);

		return lance;
	}

}
