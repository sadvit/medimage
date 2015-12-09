'use strict';

angular.module('medimage').controller('imagesController', ['$scope', 'imageService', function ($scope, imageService) {

  $scope.openImageModal = function (image) {
    angular.element('#imageBox').attr('src', 'http://localhost:8080/images/' + image);
    angular.element('#lightBox').lightbox();
    console.log(image);
  };

  $scope.deleteImage = function (image) {
    console.log(image);
  };

  $scope.processImage = function (image) {
    console.log(image);
  };

  $scope.infoImage = function (image) {
    console.log(image);
  };

  this.init = function () {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  this.init();

}]);
