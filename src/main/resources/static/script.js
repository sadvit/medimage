'use strict';

angular.module('medimage', ['ngRoute', 'restangular', 'ngWebSocket']);

angular.module('medimage').controller('indexController', ['$scope', '$http', 'Restangular', '$websocket', function ($scope, $http, Restangular, $websocket) {

    $scope.auth = function () {

        var encodedData = window.btoa($scope.login + ':' + $scope.password);
        Restangular.all('auth/login').doGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            console.log(result);
        }, function (error) {
            console.log(error);
        });

    };

    $scope.test = function () {

        Restangular.one('process/blur', 'cecb4671-9679-452b-9e6f-aadab15db7b1').doGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            console.log(result);
        }, function (error) {
            console.log(error);
        });

    };

    var dataStream = $websocket('ws://localhost:8080/process');

    dataStream.send({
        name: 'sadvit',
        role: 'admin'
    });

    dataStream.onMessage(function(message) {
        console.log(message);
    });

}]);