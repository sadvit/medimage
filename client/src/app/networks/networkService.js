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

  this.learn = function (networkId, params, callback) {
    Restangular.one('networks/learn', networkId).customPOST(params).then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

}]);
