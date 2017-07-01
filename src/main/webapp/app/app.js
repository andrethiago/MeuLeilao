angular.module('leilao', ['ngRoute', 'ngMessages', 'ngLocale', 'ui.bootstrap',]);

angular.module('leilao').config(function($httpProvider){
	$httpProvider.interceptors.push('TrataErroInterceptor');
	$httpProvider.interceptors.push('LoadingInterceptor');
	$httpProvider.interceptors.push('InterceptorMessagesHandler');
	$httpProvider.interceptors.push('InterceptorErrorHandler');
});