'use strict';

angular.module('medimage').service('processService', ['Restangular', function (Restangular) {

  this.processChain = function (chainRequest, callback) {
    Restangular.all('process').customPOST(chainRequest).then(function (data) {
      callback(data.plain());
    });
  };

}]);
