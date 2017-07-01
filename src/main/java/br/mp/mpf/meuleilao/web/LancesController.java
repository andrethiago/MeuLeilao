package br.mp.mpf.meuleilao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.mp.mpf.meuleilao.Leilao;
import br.mp.mpf.meuleilao.Usuario;
import br.mp.mpf.meuleilao.infra.web.Resultado;
import br.mp.mpf.meuleilao.leilao.ConsultaLeiloesService;
import br.mp.mpf.meuleilao.leilao.LanceTO;
import br.mp.mpf.meuleilao.leilao.RegistradorLanceService;

@Controller
@RequestMapping("/lances")
public class LancesController {

	@Autowired
	private ConsultaLeiloesService consultaLeiloes;

	@Autowired
	private RegistradorLanceService registradorLance;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Resultado incluir(@RequestBody LanceTO to) {
		//to.getItem().setDono(cadastroUsuario.consutarPorId(1L));
		//criadorLeilao.criar(to.getItem(), to.getDataInicio(), to.getDataFim());
		Leilao leilao = consultaLeiloes.consultarPorId(to.getLeilao());
		registradorLance.fazLance(new Usuario(to.getUsuario()), to.getValor(), leilao);
		return new Resultado("Lance realizado com sucesso!");
	}

}
