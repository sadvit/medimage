'use strict';

angular.module('medimage', ['ngRoute', 'restangular', 'ngWebSocket']);

var start, end;

angular.module('medimage').service('webSocketImages', ['$websocket', function ($websocket) {

    var self = this;
    var ws = $websocket('ws://localhost:8080/realtime/images');

    this.registerHandler = function (handler) {
      self.handler = handler;
    };

    this.request = function (id) {
        start = new Date().getTime();
        ws.send(id);
    };

    this.imageRecieve = function (image) {
        if (self.handler)
            self.handler(image);
    };

    ws.onMessage(function (request) {
        var image = request.data;
        self.imageRecieve(image);
    });

}]);

angular.module('medimage').service('webSocketGateway', ['$websocket', function ($websocket) {

    var self = this;
    var ws = $websocket('ws://localhost:8080/realtime/gateway');

    var callbacks = {};

    this.request = function (data, callback) {
        var id = self.guid();
        callbacks[id] = callback;
        var wrapper = {
            id: id,
            message: data
        };
        ws.send(wrapper);
    };

    this.jsonRecieve = function (data) {
        if (data && data.id) {
            if (callbacks[data.id]) {
                callbacks[data.id](data);
                delete callbacks[data.id];
            }
        }
    };

    ws.onMessage(function (request) {
        var data = request.data;
        self.jsonRecieve(data);
    });

    this.guid = function () {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    };

}]);

angular.module('medimage').controller('indexController', ['$scope', '$http', 'Restangular', 'webSocketGateway', 'webSocketImages', function ($scope, $http, Restangular, webSocketGateway, webSocketImages) {

    $scope.auth = function () {

        var encodedData = window.btoa($scope.login + ':' + $scope.password);
        Restangular.all('auth/login').doGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (result) {
            console.log(result);
        }, function (error) {
            console.log(error);
        });

    };

    $scope.test = function () {
        webSocketImages.registerHandler(function (image) {
            document.getElementById('image').src = URL.createObjectURL(image);
            end = new Date().getTime();
            var v = end - start;
            console.log('res: ' + v);
        });
        webSocketImages.request('cecb4671-9679-452b-9e6f-aadab15db7b1');
    };

}]);