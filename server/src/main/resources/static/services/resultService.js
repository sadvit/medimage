'use strict';

angular.module('medimage').service('resultService', ['Restangular', function (Restangular) {

  this.getResults = function (callback) {
    Restangular.all('results').getList().then(function (data) {
      callback(data.plain());
    });
  };

  this.saveResults = function (networkId, result, callback) {
    Restangular.one('results', networkId).customPOST(result).then(function () {
      callback();
    });
  };

}]);
