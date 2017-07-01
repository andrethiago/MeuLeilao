package br.mp.mpf.meuleilao;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.mp.mpf.meuleilao.infra.spring.MainConfig;
import br.mp.mpf.meuleilao.leilao.CriadorLeilaoService;
import br.mp.mpf.meuleilao.usuario.CadastroUsuarioService;

public class CargaInicialEntidades {

	private ApplicationContext ctx;
	private CadastroUsuarioService cadastroUsuario;
	private CriadorLeilaoService criadorLeilao;

	public CargaInicialEntidades(ApplicationContext ctx) {
		this.ctx = ctx;
		this.cadastroUsuario = ctx.getBean(CadastroUsuarioService.class);
		this.criadorLeilao = ctx.getBean(CriadorLeilaoService.class);
	}

	public static void main(String[] args) {
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

	public void criarLeiloes() {
		Item kindle = new Item();
		kindle.setNome("Amazon Kindle");
		kindle.setValorMinimo(BigDecimal.valueOf(200.0));
		kindle.setDono(cadastroUsuario.consutarPorId(3L));

		Item iphone4 = new Item();
		iphone4.setNome("Iphone 4");
		iphone4.setValorMinimo(BigDecimal.valueOf(600.0));
		iphone4.setDono(cadastroUsuario.consutarPorId(2L));

		Item gibiCavaleiroDasTrevas = new Item();
		gibiCavaleiroDasTrevas.setNome("HQ Batman - O Cavaleiro ds Trevas");
		gibiCavaleiroDasTrevas.setValorMinimo(BigDecimal.valueOf(110.0));
		gibiCavaleiroDasTrevas.setDono(cadastroUsuario.consutarPorId(1L));

		Item autografoZico = new Item();
		autografoZico.setNome("Camisa autografada pelo Zico");
		autografoZico.setValorMinimo(BigDecimal.valueOf(10000.0));
		autografoZico.setDono(cadastroUsuario.consutarPorId(2L));

		Date dataInicio = new Date();
		LocalDate localDate = LocalDate.fromDateFields(dataInicio);
		criadorLeilao.criar(kindle, dataInicio, localDate.plusDays(30).toDate());
		criadorLeilao.criar(iphone4, localDate.plusDays(2).toDate(), localDate.plusDays(15).toDate());
		criadorLeilao.criar(gibiCavaleiroDasTrevas, dataInicio, localDate.plusDays(10).toDate());
		criadorLeilao.criar(autografoZico, localDate.minusDays(5).toDate(), localDate.plusDays(7).toDate());
	}

}
