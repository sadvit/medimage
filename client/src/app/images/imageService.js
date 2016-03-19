'use strict';

angular.module('medimage').service('imageService', ['Restangular', function (Restangular) {

  this.getImages = function (callback) {
    Restangular.all('images').getList().then(function (result) {
      callback(result.plain());
    }, function (error) {
      callback(error.plain());
    });
  };

}]);
