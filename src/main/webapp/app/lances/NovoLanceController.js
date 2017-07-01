angular.module('leilao').controller('NovoLanceController', function ($scope, $routeParams, $location, LeiloesRestService, LancesRestService){
	
	$scope.leilao =  {};
	
	LeiloesRestService.getLeilao($routeParams.id).then(
		function success(resposta) {
			$scope.leilao = resposta.data.dados;
		},
		function error(erro) {
			console.log("Erro ao recuperar leilão para o lance.", erro);
		}
	);
	
	$scope.enviar = function(valor) {
		$scope.lanceForm.$setDirty();
		if($scope.lanceForm.$valid) {
			var lance = {
					'leilao' : $scope.leilao.id,
					'valor' : valor,
					'usuario' : 1
			};
			LancesRestService.incluir(lance).then(
					function success(resposta) {
						$location.path('/todos');
					},
					function error(resposta) {}
			);
			
		}
	}
	
});