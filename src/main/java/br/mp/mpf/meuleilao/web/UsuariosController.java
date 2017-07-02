package br.mp.mpf.meuleilao.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
import br.mp.mpf.meuleilao.usuario.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private VisualizadorResumoLeiloesService resumoLeiloesService;

	@RequestMapping(value = "/atual", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado usuarioAtual(Principal principal) {
		String email = principal.getName();
		Usuario usuario = cadastroUsuario.consutarPorEmail(email);
		Map<String, Object> visaoUsuario = new HashMap<>();
		visaoUsuario.put("id", usuario.getId());
		visaoUsuario.put("nome", usuario.getNome());
		visaoUsuario.put("email", usuario.getEmail());
		return new Resultado(visaoUsuario);
	}

	@RequestMapping(value = "/{usuario}/leiloes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado leiloes(@PathVariable Long usuario) {
		return new Resultado(resumoLeiloesService.visualizaTodosLeiloes(new Usuario(usuario)));
	}

}
