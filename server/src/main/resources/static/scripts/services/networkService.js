'use strict';

angular.module('medimage').service('networkService', ['Restangular', function (Restangular) {

  this.getNetworks = function (callback) {
    Restangular.all('medimage/networks').getList().then(function (data) {
      callback(data.plain());
    });
  };

}]);
