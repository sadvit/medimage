'use strict';

angular.module('medimage').controller('chainsController', ['$scope', 'chainsService', 'imageService', function ($scope, chainsService, imageService) {

  var self = this;

  $scope.chains = {};
  $scope.images = [];

  self.init = function () {
    chainsService.getChains(function (chains) {
      $scope.chains = chains;
    });
    // TODO remove
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  self.init();

  $scope.selectChain = function (chain) {

  };

  $scope.showChainsBlock = function () {
    angular.element('.chains-block').toggleClass('show');
  };

  $scope.showInputBlock = function () {
    angular.element('.input-block').toggleClass('show');
  };

  $scope.showOutputBlock = function () {
    angular.element('.output-block').toggleClass('show');
  };

  $scope.selectedImages = [];

  $scope.selectUserImage = function (imageId) {
    angular.element('.' + imageId).toggleClass('selected');
    $scope.selectedImages.push(imageId);
  };

}]);
