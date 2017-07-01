angular.module('leilao').factory('TrataErroInterceptor', function($q, $location){
	return {
		responseError: function (rejection) {
			console.log('responseError');
			if(rejection.status == 404 || rejection.status == 403) {
				$location.path('/erro');
			}
			return $q.reject(rejection);
		}
	};
});