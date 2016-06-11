'use strict';

angular.module('medimage').service('processService', ['Restangular', function (Restangular) {

  this.processChain = function (chainRequest, callback) {
    Restangular.all('medimage/process/images').customPUT(chainRequest).then(function (data) {
      var images = data.plain();
      var _images = [];
      images.forEach(function (image) {
        _images.push(image.id);
      });
      callback(_images);
    });
  };

}]);
