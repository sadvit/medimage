'use strict';

angular.module('medimage').controller('imagesController', ['$scope', 'imageService', '$state', '$rootScope', 'FileUploader', function ($scope, imageService, $state, $rootScope, FileUploader) {

  $scope.uploader = new FileUploader({
    url: network_address + '/upload'
  });

  $scope.uploader.onCompleteItem = function() {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  $scope.uploader.onAfterAddingFile = function() {
    $scope.uploader.uploadAll();
  };

  $scope.openImageModal = function (image) {
    angular.element('#imageBox').attr('src', $rootScope.network_address + '/images/' + image);
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

  $scope.uploadImages = function () {

  };

  this.init = function () {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  this.init();

}]);
