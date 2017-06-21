package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

public class RegistradorLanceServiceTest {

	@Test
	public void lanceDevePossuirData() {
		RegistradorLanceService registradorLanceService = new RegistradorLanceService();

		Lance lance = registradorLanceService.fazLance(new Usuario("nome", "email@email.com", "123456"), BigDecimal.valueOf(50.0), new Leilao());

		assertNotNull(lance.getData());
	}

}
