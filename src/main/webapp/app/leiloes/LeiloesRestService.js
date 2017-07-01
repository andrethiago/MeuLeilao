angular.module('leilao').factory('LeiloesRestService', function($http) {
	return {
		getLeiloes : function() {
			return $http.get('/leilao/leiloes');
		},
		
		getLeiloesUsuario : function(usuario) {
			return $http.get('/leilao/usuarios/'+ usuario + '/leiloes')
		},
		
		getLeilao: function(id) {
			return $http.get('/leilao/leiloes/' + id);
		},
		
		incluir : function(leilao) {
			return $http.post('/leilao/leiloes', leilao);
		}
		
	
	}
});