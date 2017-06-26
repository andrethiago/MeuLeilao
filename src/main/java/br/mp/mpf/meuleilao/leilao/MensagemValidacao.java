package br.mp.mpf.meuleilao.leilao;

public interface MensagemValidacao {

	public static final String NAO_DEVE_SER_POSSIVEL_FAZER_LANCES_LEILAO_FECHADO =
		"Não é possível fazer lances em um leilão fechado.";
	public static final String NAO_DEVE_SER_POSSIVEL_DAR_LANCE_MENOR_QUE_MAIOR =
		"Não é possível dar um lance menor do que o maior já existente.";
	public static final String NAO_DEVE_SER_POSSIVEL_USUARIO_DAR_DOIS_LANCES_SEGUIDOS_LEILAO =
		"Não é possível o mesmo usuário dar dois lances seguidos no leilão.";

}
