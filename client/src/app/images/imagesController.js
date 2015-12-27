'use strict';

angular.module('medimage').controller('imagesController', ['$scope', 'imageService', '$state', function ($scope, imageService, $state) {

  $scope.openImageModal = function (image) {
    angular.element('#imageBox').attr('src', 'http://localhost:8080/images/' + image);
    angular.element('#lightBox').lightbox();
    console.log(image);
  };

  $scope.deleteImage = function (image) {
    console.log('delete ' + image);
  };

  $scope.processImage = function (image) {
    $state.go('process', {imageId: image});
    console.log('process ' + image);
  };

  $scope.infoImage = function (image) {
    $state.go('statistics', {imageId: image});
  };

  this.init = function () {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  this.init();

}]);
