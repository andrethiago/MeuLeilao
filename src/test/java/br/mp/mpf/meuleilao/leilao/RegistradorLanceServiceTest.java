package br.mp.mpf.meuleilao.leilao;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.mp.mpf.meuleilao.Lance;
import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class RegistradorLanceServiceTest {

	@Mock
	private LanceRepository repository;

	@InjectMocks
	private RegistradorLanceService registradorLanceService;

	@Test
	public void lanceDevePossuirData() {
		Date daqui2Semanas = LocalDate.now().plusWeeks(2).toDate();
		Leilao leilao = LeilaoTestDataBuider.umLeilao().comDataFim(daqui2Semanas).build();

		Lance lance = registradorLanceService.fazLance(new Usuario("nome", "email@email.com", "123456"), BigDecimal.valueOf(50.0), leilao);

		assertNotNull(lance.getData());

		verify(repository, times(1)).incluir(lance);
	}

}
