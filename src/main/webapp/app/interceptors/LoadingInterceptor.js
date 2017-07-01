angular.module('leilao').factory('LoadingInterceptor', function($q, $rootScope){
	return {
		request: function(config) {
			$rootScope.carregando = true;
			return config;
		},
		requestError: function(rejection) {
			$rootScope.carregando = false;
			return $q.reject(rejection);
		},
		response: function (response) {
			$rootScope.carregando = false;
			return response;
		},
		responseError: function (rejection) {
			$rootScope.carregando = false;
			return $q.reject(rejection);
		}
	};
});