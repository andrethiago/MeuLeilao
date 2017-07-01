package br.mp.mpf.meuleilao.leilao;

import br.mp.mpf.meuleilao.infra.BusinessException;

public class LanceInvalidoException extends BusinessException {

	private static final long serialVersionUID = 473176381172975423L;

	public LanceInvalidoException(String mensagem) {
		super(mensagem);
	}

}
