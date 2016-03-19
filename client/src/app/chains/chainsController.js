'use strict';

angular.module('medimage').controller('chainsController', ['$scope', 'chainsService', 'imageService', function ($scope, chainsService, imageService) {

  var self = this;

  $scope.chains = {};
  $scope.chainElements = [];
  $scope.images = [];
  $scope.selectedChain = {};

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

  $scope.showChainsBlock = function (event, element) {
    var isOpened = angular.element('.chains-block').hasClass('show');
    if (!isOpened) {
      angular.element('.chains-block').toggleClass('show');
    }
    $scope.selectedChain = element;
    $scope.chainElements = element.chainElements;
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
