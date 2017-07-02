angular.module('leilao').controller('LeiloesController', function ($scope, $routeParams, LeiloesRestService, InformacaoSessaoService){
	
	
	$scope.leilao = null;
	$scope.leiloes = [];
	$scope.usuarioAtual = {}
	
	var usuario = $routeParams.idUsuario;
	InformacaoSessaoService.usuario().then(
		function success(resposta) {
			$scope.usuarioAtual = resposta;
		}
	);
	
	
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