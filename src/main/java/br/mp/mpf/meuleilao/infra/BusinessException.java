package br.mp.mpf.meuleilao.infra;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 115123562589396961L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}

}
