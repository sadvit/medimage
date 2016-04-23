'use strict';

angular.module('medimage').service('recognizeService', ['Restangular', function (Restangular) {

  this.learn = function (result, callback) {
    Restangular.all('recognize').customPUT(result).then(function () {
      callback();
    });
  };

  this.recognize = function (networkId, images, callback) {
    Restangular.one('recognize', networkId).customPOST(images).then(function (data) {
      callback(data.plain());
    });
  };

}]);
