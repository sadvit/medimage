'use strict';

angular.module('medimage').service('chainService', ['Restangular', function (Restangular) {

  this.saveChain = function (chain, callback) {
    Restangular.all('medimage/chains').customPOST(chain).then(function (data) {
      callback(data.plain());
    });
  };

  this.getChains = function (callback) {
    Restangular.all('medimage/chains').getList().then(function (data) {
      callback(data.plain());
    });
  };

}]);
