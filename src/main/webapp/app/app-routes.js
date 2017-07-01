// Configurando as rotas
angular.module('leilao').config(function($routeProvider) {
	
	$routeProvider.when("/todos", {
		templateUrl: "leiloes/leiloes.html"
	});
	
	$routeProvider.when("/novoLeilao", {
		templateUrl: "leiloes/novoLeilao.html"
	});
	
	$routeProvider.when("/meusLeiloes/:idUsuario", {
		templateUrl: "leiloes/leiloes.html"
	});
	
	$routeProvider.when("/leilao/:id/novoLance", {
		templateUrl: "lances/novoLance.html"
	});
	
	$routeProvider.when("/erro", {
		templateUrl: "erro.html"
	});
	
	$routeProvider.otherwise({
		redirectTo: '/todos'
	});
	
});