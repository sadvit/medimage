'use strict';

angular.module('medimage').service('imageService', ['Restangular', function (Restangular) {

  this.getImages = function (callback) {
    Restangular.all('images').getList().then(function (result) {
      callback(result.plain());
    });
  };

  this.saveImages = function (images, callback) {
    Restangular.all('temp').customPOST(images).then(function (result) {
      callback(result.plain());
    });
  };

  this.deleteImage = function (imageId, callback) {
    Restangular.one('images', imageId).remove().then(function () {
      callback();
    });
  }

}]);
