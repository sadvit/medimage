'use strict';

angular.module('medimage').service('chainsService', ['Restangular', function (Restangular) {

  this.acceptChain = function (imageId, chain, callback) {
    Restangular.one('chains/one', imageId).customPOST(chain).then(function (data) {
      callback(data.plain().id);
    });
  };

  this.saveChain = function (name, chain, callback) {
    Restangular.one('chains', name).customPOST(chain).then(function (data) {
      callback();
    });
  };

  this.getChains = function (callback) {
    Restangular.all('chains').getList().then(function (data) {
      callback(data.plain());
    });
  };

  this.processImages = function (chainId, images, callback) {
    Restangular.one('chains/many', chainId).customPOST(images).then(function (data) {
      callback(data.plain());
    });
  };

}]);
