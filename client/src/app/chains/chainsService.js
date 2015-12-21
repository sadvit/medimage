'use strict';

angular.module('medimage').service('chainsService', ['Restangular', function (Restangular) {

  this.acceptChain = function (id, chain, callback) {
    Restangular.one('chains', id).customPOST(chain).then(function (data) {
      callback(data.id);
    });
  };

  this.saveChain = function (chain, callback) {
    Restangular.all('chains').customPOST(chain).then(function (data) {
      callback(data);
    });
  };

}]);