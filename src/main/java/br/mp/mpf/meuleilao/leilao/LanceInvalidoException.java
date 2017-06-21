package br.mp.mpf.meuleilao.leilao;

public class LanceInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 473176381172975423L;

	public LanceInvalidoException(String mensagem) {
		super(mensagem);
	}

}
