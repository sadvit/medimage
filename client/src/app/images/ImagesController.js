'use strict';

angular.module('medimage').controller('imagesController', ['$scope', 'imageService', function ($scope, imageService) {

  this.init = function () {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  this.init();

}]);
