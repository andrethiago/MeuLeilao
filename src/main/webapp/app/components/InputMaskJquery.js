/**
 * Diretiva
 * @memberOf Diretiva
 * @namespace toggleClass
 * @param {string} toggle-class Nome da classe
 * @param {string} toggle-class-target Seletor CSS
 * @param {string} toggle-type Se é 'hover' ou 'click'
 * @desc Adiciona mascara nos campos (Ex: CPF, CNPJ, etc)
 *      https://github.com/RobinHerbots/jquery.inputmask
 *      
 * @example
 * <input input-mask-jquery>
 */
(function (){
    'use strict';
    angular
            .module('leilao')
            .directive('inputMaskJquery', Directive);

    function Directive(){
        return {
            restrict: "A",
            link: link
        };
        function link(scope, element){
            element.inputmask();
        }
    }
})();