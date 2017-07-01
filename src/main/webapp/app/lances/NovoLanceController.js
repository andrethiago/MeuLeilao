angular.module('leilao').controller('NovoLanceController', function ($scope, $routeParams, LeiloesRestService){
	
	$scope.leilao =  {};
	
	LeiloesRestService.getLeilao($routeParams.id).then(
		function success(resposta) {
			$scope.leilao = resposta.data.dados;
		},
		function error(erro) {
			console.log("Erro ao recuparar leilão para o lance.", erro);
		}
	);
	
	$scope.salvar = function(leilao) {
		console.log('leilão', leilao);
		LeiloesRestService.incluir(leilao).then(
			function success(resposta) {},
			function error(resposta) {}
		);
	}
	
});