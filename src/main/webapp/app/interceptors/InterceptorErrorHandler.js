angular
        .module('leilao')
        .factory('InterceptorErrorHandler',
                function ($q, $rootScope){

                    $rootScope.errorsSistema = {msg: ""};
                    var txt = '';

                    var interceptor = {
                        'request': function (config){
                            $rootScope.errorsSistema = [];
                            return config;
                        },
                        'response': function (response){
                            return response;
                        },
                        'requestError': function (rejection){
                            return $q.reject(rejection);
                        },
                        'responseError': function (rejection){
                        	console.log('interceptor error', rejection);
                            if (rejection.data) {
                                txt = (rejection.data.message) ? rejection.data.message : rejection.statusText;

                                if (typeof toastr !== "undefined") {
                                    toastr.options.closeButton = true;
                                    toastr.options.timeOut = "10000";
                                    toastr.error(txt);
                                }
                            }
                            return $q.reject(rejection);
                        }
                    };
                    return interceptor;
                });