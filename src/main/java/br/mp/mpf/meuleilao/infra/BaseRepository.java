package br.mp.mpf.meuleilao.infra;

import java.lang.reflect.ParameterizedType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRepository<E> {

	@Autowired
	SessionFactory sessionFactory;

	protected Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public E incluir(E entidade) {
		getSession().save(entidade);
		return entidade;
	}

	public E alterar(E entidade) {
		getSession().update(entidade);
		return entidade;
	}

	public void excluir(E entidade) {
		getSession().delete(entidade);
	}

	@SuppressWarnings("unchecked")
	public E consultarPorId(Long id) {
		E e = (E) getSession().get(getClazz(), id);
		if (e != null) {
			return e;
		}
		throw new EntidadeNaoEncontradaException("Não existe objeto com esse id.");
	}

	@SuppressWarnings("unchecked")
	private Class<E> getClazz() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
