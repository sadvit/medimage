'use strict';

angular.module('medimage').service('resultService', ['Restangular', function (Restangular) {

  this.getResults = function (callback) {
    Restangular.all('medimage/results').getList().then(function (data) {
      callback(data.plain());
    });
  };

  this.saveResults = function (networkId, result, callback) {
    Restangular.one('medimage/results', networkId).customPOST(result).then(function () {
      callback();
    });
  };

}]);
