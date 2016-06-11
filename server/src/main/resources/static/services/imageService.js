'use strict';

angular.module('medimage').service('imageService', ['Restangular', function (Restangular) {

  this.getImage = function (imageId, callback) {
    Restangular.one('medimage/images', imageId).get().then(function (data) {
      callback(data.plain());
    });
  };

  this.getImages = function (callback) {
    Restangular.all('medimage/images').getList().then(function (data) {
      callback(data.plain());
    });
  };

  this.deleteImage = function (imageId, callback) {
    Restangular.one('medimage/images', imageId).remove().then(function () {
      callback();
    });
  }

}]);
