'use strict';

angular.module('medimage').service('loginService', ['Restangular', function (Restangular) {

    this.auth = function (login, pass, success, error) {
        var encodedData = window.btoa(login + ':' + pass);
        Restangular.all('auth/login').customGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (resp) {
            success(resp);
        }, function (resp) {
            error(resp);
        });
    };

}]);