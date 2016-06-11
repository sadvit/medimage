'use strict';

angular.module('medimage').service('tempService', ['Restangular', function (Restangular) {

  this.saveTempImages = function (images, callback) {
    Restangular.all('medimage/temp').customPOST(images).then(function (result) {
      callback(result.plain());
    });
  };

}]);
