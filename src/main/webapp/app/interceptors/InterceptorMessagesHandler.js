angular
        .module('leilao')
        .factory('InterceptorMessagesHandler',
                function ($q, $rootScope){

                    $rootScope.msgsSucesso = {msg: ""};
                    $rootScope.msgsAviso = {msg: ""};

                    var interceptor = {
                        'request': function (config){
                            if (config.method != "GET") {
                                $rootScope.msgsSucesso = [];
                                $rootScope.msgsAviso = [];
                            }
                            return config;
                        },
                        'response': function (response){
                            if (response.data.mensagem) {
                                if (typeof toastr !== "undefined") {
                                    if (response.data.sucesso) {
                                        toastr.success(response.data.mensagem);
                                    } else {
                                        toastr.warning(response.data.mensagem);
                                    }
                                }

                            }
                            return response;
                        },
                        'requestError': function (rejection){
                            return $q.reject(rejection);
                        },
                        'responseError': function (rejection){
                            return $q.reject(rejection);
                        }
                    };
                    return interceptor;
                });