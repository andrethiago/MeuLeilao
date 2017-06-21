package br.mp.mpf.meuleilao.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mp.mpf.meuleilao.Usuario;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Transactional
	public void cadastrar(String nome, String email, String password) {
		Usuario usuario = new Usuario(nome, email, password);
		repository.incluir(usuario);
	}

	public List<Usuario> listar() {
		return repository.consultarTodos();
	}

}
