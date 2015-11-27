'use strict';

angular.module('medimage', ['ngRoute', 'restangular', 'ngWebSocket']);

var start, end;

angular.module('medimage').service('webSocketImages', ['$websocket', function ($websocket) {

    var self = this;
    var ws = $websocket('ws://localhost:8080/images');

    this.request = function (imageId, callback) {
        start = new Date().getTime();
        var id = self.guid();
        self.callback = callback;
        var wrapper = {
            id: id,
            message: data
        };
        ws.send(wrapper);
    };

    this.imageRecieve = function (data) {
        var id = data.slice(0, 36);
        var image = 'data:image/jpeg;base64,' + data.slice(37, data.length - 1);
        if (callbacks[id]) {
            callbacks[id](image);
            delete callbacks[id];
        }
    };

    ws.onMessage(function (request) {
        var image = request.data;
        self.imageRecieve(image);
    });

    // ---------------------------------

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

angular.module('medimage').service('webSocketGateway', ['$websocket', function ($websocket) {

    var self = this;
    var ws = $websocket('ws://localhost:8080/gateway');

    var callbacks = {};

    this.imageRequest = function (data, callback) {
        start = new Date().getTime();
        var id = self.guid();
        callbacks[id] = callback;
        var wrapper = {
            id: id,
            message: data
        };
        ws.send(wrapper);
    };

    this.operationRequest = function (data, callback) {
        var id = self.guid();
        callbacks[id] = callback;
        var wrapper = {
            id: id,
            message: data
        };
        ws.send(wrapper);
    };

    // ---------------------------------

    this.imageRecieve = function (data) {
        var id = data.slice(0, 36);
        var image = 'data:image/jpeg;base64,' + data.slice(37, data.length - 1);
        if (callbacks[id]) {
            callbacks[id](image);
            delete callbacks[id];
        }
    };

    this.jsonRecieve = function (data) {
        if (data && data.id) {
            if (callbacks[data.id]) {
                callbacks[data.id](data);
                delete callbacks[data.id];
            }
        }
    };

    // ---------------------------------

    ws.onMessage(function (request) {
        var data = request.data;
        try {
            var json = JSON.parse(data);
            self.jsonRecieve(json);
        } catch(e) {
            self.imageRecieve(data);
        }
    });

    // ---------------------------------

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

angular.module('medimage').controller('indexController', ['$scope', '$http', 'Restangular', 'webSocketGateway', function ($scope, $http, Restangular, webSocketGateway) {

    var canvas = document.getElementById("c");
    var ctx = canvas.getContext("2d");

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
            id: 'cecb4671-9679-452b-9e6f-aadab15db7b1',
            action: 'getImage'
        };
        webSocketGateway.imageRequest(data, function (image) {
            //document.getElementById('image').src = image;

            var imageObj = new Image();
            imageObj.onload = function() {
                ctx.drawImage(imageObj, 0, 0);
            };
            imageObj.src = image;

            end = new Date().getTime();
            var v = end - start;
            console.log('result: ' + v);
        });
    };

}]);