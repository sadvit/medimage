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
  };

  $scope.deleteImage = function (image) {
    imageService.deleteImage(image, function () {
      var index = $scope.images.indexOf(image);
      $scope.images.splice(index, 1);
    })
  };

  $scope.processImage = function (image) {
    $state.go('process', {imageId: image});
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

  $scope.safeApply = function(fn) {
    var phase = $scope.$$phase;
    if(phase == '$apply' || phase == '$digest') {
      if(fn && (typeof(fn) === 'function')) {
        fn();
      }
    } else {
      $scope.$apply(fn);
    }
  };

  this.init();

}]);
