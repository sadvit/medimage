'use strict';

angular.module('medimage').service('networkService', ['Restangular', function (Restangular) {

  this.getNetworks = function (callback) {
    Restangular.all('networks').getList().then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

  this.recognize = function (networkId, images, callback) {
    Restangular.one('networks/recognize', networkId).customPOST(images).then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

  this.learn = function (params, callback) {
    Restangular.all('networks/learn').customPOST(params).then(function () {
      callback();
    }, function (error) {
      callback(error);
    });
  };

}]);
