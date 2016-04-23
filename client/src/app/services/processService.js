'use strict';

angular.module('medimage').service('processService', ['Restangular', function (Restangular) {

  this.processChain = function (chainRequest, callback) {
    Restangular.all('process/images').customPUT(chainRequest).then(function (data) {
      callback(data.plain());
    });
  };

}]);
