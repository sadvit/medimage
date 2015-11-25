'use strict';

angular.module('medimage', ['ngRoute', 'restangular']);

angular.module('medimage').controller('indexController', ['$scope', '$http', 'Restangular', function ($scope, $http, Restangular) {

    $scope.auth = function () {

        var encodedData = window.btoa($scope.login + ':' + $scope.password);
        Restangular.all('auth/login').customGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            console.log(result);
        }, function (error) {
            console.log(error);
        });

    }

}]);