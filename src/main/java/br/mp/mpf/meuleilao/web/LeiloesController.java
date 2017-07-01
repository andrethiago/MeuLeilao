package br.mp.mpf.meuleilao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.mp.mpf.meuleilao.infra.web.Resultado;
import br.mp.mpf.meuleilao.leilao.ConsultaLeiloesService;
import br.mp.mpf.meuleilao.leilao.CriadorLeilaoService;
import br.mp.mpf.meuleilao.leilao.LeilaoTO;
import br.mp.mpf.meuleilao.leilao.VisualizadorResumoLeiloesService;
import br.mp.mpf.meuleilao.usuario.CadastroUsuarioService;

@Controller
@RequestMapping("/leiloes")
public class LeiloesController {

	@Autowired
	private CriadorLeilaoService criadorLeilao;

	@Autowired
	private VisualizadorResumoLeiloesService resumoLeiloesService;

	@Autowired
	private ConsultaLeiloesService consultaLeiloes;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado leiloes() {
		return new Resultado(resumoLeiloesService.visualizaTodosLeiloesAbertos());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado leilao(@PathVariable Long id) {
		return new Resultado(consultaLeiloes.consultarPorId(id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado incluir(@RequestBody LeilaoTO to) {
		//to.getItem().setDono(cadastroUsuario.consutarPorId(1L));
		//criadorLeilao.criar(to.getItem(), to.getDataInicio(), to.getDataFim());
		return new Resultado("Leilão incluído com sucesso!");
	}

}
