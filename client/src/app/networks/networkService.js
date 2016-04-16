'use strict';

angular.module('medimage').service('networkService', ['Restangular', function (Restangular) {

  this.getNetworks = function (callback) {
    Restangular.all('networks').getList().then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

  this.recognize = function (networkId, chainId, images, callback) {
    Restangular.one('networks/recognize', networkId).one(chainId).customPOST(images).then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

  this.learn = function (networkId, chainId, params, callback) {
    Restangular.one('networks/learn', networkId).one(chainId).customPOST(params).then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

}]);
