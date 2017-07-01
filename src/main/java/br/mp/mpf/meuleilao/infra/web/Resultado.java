package br.mp.mpf.meuleilao.infra.web;

import br.mp.mpf.meuleilao.infra.ErrorInfo;

public class Resultado {

	private Object dados;
	private ErrorInfo erro;
	private boolean sucesso = true;
	private String mensagem;

	public Resultado() {}

	public Resultado(Object dados) {
		super();
		this.dados = dados;
	}

	public Resultado(String mensagem) {
		this.mensagem = mensagem;
	}

	public Resultado(Object dados, String mensagem) {
		super();
		this.dados = dados;
		this.mensagem = mensagem;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public Object getDados() {
		return dados;
	}

	public void setDados(Object dados) {
		this.dados = dados;
	}

	public ErrorInfo getErro() {
		return erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void addErro(ErrorInfo erro) {
		this.erro = erro;
		this.sucesso = false;
	}

}
