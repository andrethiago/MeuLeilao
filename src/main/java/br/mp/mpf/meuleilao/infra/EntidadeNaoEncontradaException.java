package br.mp.mpf.meuleilao.infra;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 5646248295953656217L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
