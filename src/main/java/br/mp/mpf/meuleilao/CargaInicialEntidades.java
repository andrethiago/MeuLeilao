package br.mp.mpf.meuleilao;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.mp.mpf.meuleilao.infra.spring.MainConfig;
import br.mp.mpf.meuleilao.leilao.CriadorLeilaoService;
import br.mp.mpf.meuleilao.leilao.RegistradorLanceService;
import br.mp.mpf.meuleilao.usuario.CadastroUsuarioService;

public class CargaInicialEntidades {

	private ApplicationContext ctx;
	private CadastroUsuarioService cadastroUsuario;
	private CriadorLeilaoService criadorLeilao;
	private RegistradorLanceService registradorLance;

	public CargaInicialEntidades(ApplicationContext ctx) {
		this.ctx = ctx;
		this.cadastroUsuario = ctx.getBean(CadastroUsuarioService.class);
		this.criadorLeilao = ctx.getBean(CriadorLeilaoService.class);
		this.registradorLance = ctx.getBean(RegistradorLanceService.class);
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
		CargaInicialEntidades cie = new CargaInicialEntidades(ctx);

		//cie.incluirUsuarios();
		cie.criarLeiloes();
	}

	public void incluirUsuarios() {
		cadastroUsuario.cadastrar("João Silva", "joaosilva@mpf.mp.br", "123456");
		cadastroUsuario.cadastrar("José Silva", "josesilva@mpf.mp.br", "12345678");
		cadastroUsuario.cadastrar("Maria Leopoldina", "maria@mpf.mp.br", "qwerty");
		cadastroUsuario.cadastrar("Xica da Silva", "xica@mpf.mp.br", "abcdef");
	}

	public void criarLeiloes() throws InterruptedException {
		Usuario usuario01 = cadastroUsuario.consutarPorId(1L);
		Usuario usuario02 = cadastroUsuario.consutarPorId(2L);
		Usuario usuario03 = cadastroUsuario.consutarPorId(3L);
		Usuario usuario04 = cadastroUsuario.consutarPorId(4L);

		Item kindle = new Item();
		kindle.setNome("Amazon Kindle");
		kindle.setValorMinimo(BigDecimal.valueOf(200.0));
		kindle.setDono(usuario03);

		Item iphone4 = new Item();
		iphone4.setNome("Iphone 4");
		iphone4.setValorMinimo(BigDecimal.valueOf(600.0));
		iphone4.setDono(usuario02);

		Item gibiCavaleiroDasTrevas = new Item();
		gibiCavaleiroDasTrevas.setNome("HQ Batman - O Cavaleiro das Trevas");
		gibiCavaleiroDasTrevas.setValorMinimo(BigDecimal.valueOf(110.0));
		gibiCavaleiroDasTrevas.setDono(usuario01);

		Item autografoZico = new Item();
		autografoZico.setNome("Camisa autografada pelo Zico");
		autografoZico.setValorMinimo(BigDecimal.valueOf(10000.0));
		autografoZico.setDono(usuario02);

		Date dataInicio = new Date();
		LocalDate localDate = LocalDate.fromDateFields(dataInicio);

		Leilao leilaoKindle = criadorLeilao.criar(kindle, dataInicio, localDate.plusDays(30).toDate());
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(210.0), leilaoKindle);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario02, BigDecimal.valueOf(220.0), leilaoKindle);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(225.0), leilaoKindle);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario04, BigDecimal.valueOf(250.0), leilaoKindle);
		Thread.sleep(2000);

		Leilao leilaoIphone =
			criadorLeilao.criar(iphone4, localDate.minusDays(2).toDate(), localDate.plusDays(15).toDate());
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(650.0), leilaoIphone);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario03, BigDecimal.valueOf(660.0), leilaoIphone);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(670.0), leilaoIphone);
		Thread.sleep(2000);

		criadorLeilao.criar(gibiCavaleiroDasTrevas, dataInicio, localDate.plusDays(10).toDate());

		Leilao leilaoZico =
			criadorLeilao.criar(autografoZico, localDate.minusDays(5).toDate(), localDate.plusDays(7).toDate());
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(10050.0), leilaoZico);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario03, BigDecimal.valueOf(10075.0), leilaoZico);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(10100.0), leilaoZico);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario04, BigDecimal.valueOf(10200.0), leilaoZico);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario01, BigDecimal.valueOf(10500.0), leilaoZico);
		Thread.sleep(2000);
		registradorLance.fazLance(usuario03, BigDecimal.valueOf(10700.0), leilaoZico);
		Thread.sleep(2000);
	}

}
