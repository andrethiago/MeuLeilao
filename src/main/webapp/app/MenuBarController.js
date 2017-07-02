angular.module('leilao').controller('MenuBarController', function ($scope, $rootScope, InformacaoSessaoService){
	
	$scope.usuarioAtual = {};
	
	InformacaoSessaoService.usuario().then(
		function success(resposta) {
			$scope.usuarioAtual = resposta;
		}
	);
	
});