'use strict';

angular.module('medimage', ['ngRoute', 'restangular', 'ngWebSocket']);

angular.module('medimage').service('webSocketGateway', ['$websocket', function ($websocket) {

    var self = this;
    var dataStream = $websocket('ws://localhost:8080/gateway');
    var map = {};

    this.guid = function () {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    };

    this.request = function (data, callback) {
        var id = self.guid();
        data.id = id;
        map[id] = callback;
        dataStream.send(data);
    };

    dataStream.onMessage(function (request) {
        var data = JSON.parse(request.data);
        if (data && data.id) {
            console.log('Success recv data');
            map[data.id](data);
        } else {
            console.log('Incorrect data');
        }
    });

}]);

angular.module('medimage').controller('indexController', ['$scope', '$http', 'Restangular', 'webSocketGateway', function ($scope, $http, Restangular, webSocketGateway) {

    $scope.auth = function () {

        var encodedData = window.btoa($scope.login + ':' + $scope.password);
        Restangular.all('auth/login').doGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            console.log(result);
        }, function (error) {
            console.log(error);
        });

    };

    $scope.test = function () {
        var data = {
            name: 'sadvit',
            role: 'user'
        };
        webSocketGateway.request(data, function (data) {
            console.log('recv data from callback: ' + JSON.stringify(data))
        });
    };

}]);