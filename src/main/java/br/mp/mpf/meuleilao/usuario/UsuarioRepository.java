package br.mp.mpf.meuleilao.usuario;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.mp.mpf.meuleilao.Usuario;
import br.mp.mpf.meuleilao.infra.BaseRepository;

@Repository
class UsuarioRepository extends BaseRepository<Usuario> {

	@SuppressWarnings("unchecked")
	public List<Usuario> consultarTodos() {
		return getSession().createQuery("from Usuario").list();
	}

	public Usuario consultarPorEmail(String email) {
		return (Usuario) getSession().createQuery("from Usuario where email = :email")
			.setParameter("email", email)
			.uniqueResult();
	}

}
