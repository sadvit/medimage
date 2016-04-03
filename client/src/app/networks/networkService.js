'use strict';

angular.module('medimage').service('networkService', ['Restangular', function (Restangular) {

  this.getNetworks = function (callback) {
    Restangular.all('networks').getList().then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

  this.recognize = function () {

  };

}]);
