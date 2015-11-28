'use strict';

angular.module('medimage').service('loginService', ['Restangular', function (Restangular) {

    this.auth = function (login, pass, callback) {
        var encodedData = window.btoa(login + ':' + pass);
        Restangular.all('auth/login').customGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            callback(result);
        }, function (error) {
            callback(error);
        });
    };

}]);