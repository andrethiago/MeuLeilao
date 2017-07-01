angular.module('leilao').controller('NovoLeilaoController', function ($scope, $location, LeiloesRestService){
	
	$scope.leilao = null;
	
	$scope.maiorDataInicio = {};
	$scope.menorDataFim = {};
	
	$scope.dataInicialEscolhida = function() {
		if($scope.leilaoForm.inicioPeriodo.$modelValue) {
			var data = new Date($scope.leilaoForm.inicioPeriodo.$modelValue);
			data.setDate(data.getDate() + 1);
			$scope.menorDataFim = data;
		}
	}
	
	$scope.dataFinalEscolhida = function() {
		if($scope.leilaoForm.fimPeriodo.$modelValue) {
			var data = new Date($scope.leilaoForm.fimPeriodo.$modelValue);
			data.setDate(data.getDate() - 1);
			$scope.maiorDataInicio = data;
		}
	}
	
	$scope.salvar = function(leilao) {
		$scope.leilaoForm.$setDirty();
		if($scope.leilaoForm.$valid) {
			LeiloesRestService.incluir(leilao).then(
					function success(resposta) {
						$location.path('/todos');
					},
					function error(resposta) {}
			);
		}
	}
	
});