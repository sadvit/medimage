'use strict';

angular.module('medimage').controller('recognizeController', ['$scope', 'imageService', function ($scope, imageService) {

  var self = this;

  $scope.images = [];

  self.init = function () {
    // TODO remove
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  self.init();

}]);
