angular.module('leilao').controller('LeiloesController', function ($scope, $routeParams, LeiloesRestService){
	
	
	var usuario = $routeParams.idUsuario;
	
	
	$scope.leilao = null;
	$scope.leiloes = [];
	$scope.mensagemSucesso = null;
	$scope.mensagemAviso = null;
	$scope.mensagemErro = null;
	$scope.exibeFormulario = false;
	
	
	function carregarLeiloes() {
		if(usuario) {
			LeiloesRestService.getLeiloesUsuario(usuario).then(
					function success(resposta) {
						$scope.leiloes = resposta.data.dados;
					},
					function error(erro) {
						console.log("Erro ao carregar leilões.", erro)
					}
			);
		} else {
			LeiloesRestService.getLeiloes().then(
					function success(resposta) {
						$scope.leiloes = resposta.data.dados;
					},
					function error(erro) {
						console.log("Erro ao carregar leilões.", erro)
					}
			);
		}
	};
	
	carregarLeiloes();
	
	
});