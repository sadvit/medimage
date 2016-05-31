'use strict';

angular.module('medimage').service('chainService', ['Restangular', function (Restangular) {

  this.saveChain = function (chain, callback) {
    Restangular.all('chains').customPOST(chain).then(function (data) {
      callback(data.plain());
    });
  };

  this.getChains = function (callback) {
    Restangular.all('chains').getList().then(function (data) {
      callback(data.plain());
    });
  };

}]);
