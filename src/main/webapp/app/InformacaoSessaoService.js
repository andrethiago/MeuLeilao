angular.module('leilao').factory('InformacaoSessaoService', function($http, $timeout, $rootScope, $q) {
	
	var usuarioAtual;
	
	return {
		usuario : function() {
			if(usuarioAtual === undefined || usuarioAtual === null) {
				var deferred = $q.defer();
				return $http.get('/leilao/usuarios/atual').then(
						function success(resposta) {
							usuarioAtual = resposta.data.dados;
							return usuarioAtual;
						}
				);
				return deferred.promise;
			}
			return $timeout(function() {return usuarioAtual}, 200);
		}
	}
});