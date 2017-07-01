angular.module('leilao').factory('LancesRestService', function($http) {
	return {
		
		incluir : function(lance) {
			return $http.post('/leilao/lances', lance);
		}
		
	
	}
});