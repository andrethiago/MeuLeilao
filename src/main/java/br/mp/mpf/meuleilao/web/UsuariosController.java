package br.mp.mpf.meuleilao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.mp.mpf.meuleilao.Usuario;
import br.mp.mpf.meuleilao.infra.web.Resultado;
import br.mp.mpf.meuleilao.leilao.VisualizadorResumoLeiloesService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private VisualizadorResumoLeiloesService resumoLeiloesService;

	@RequestMapping(value = "/{usuario}/leiloes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado leiloes(@PathVariable Long usuario) {
		return new Resultado(resumoLeiloesService.visualizaTodosLeiloesAbertos(new Usuario(usuario)));
	}

}
