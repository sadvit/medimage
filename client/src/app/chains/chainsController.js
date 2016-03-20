'use strict';

angular.module('medimage').controller('chainsController', ['$scope', 'chainsService', 'imageService', function ($scope, chainsService, imageService) {

  var self = this;

  $scope.chains = {};
  $scope.chainElements = [];
  $scope.images = [];
  $scope.selectedChain = {};
  $scope.selectedImages = [];

  $scope.testArray = [];

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
    angular.element('div.no-data').addClass('hide');
    angular.element('.output-block').toggleClass('show');
  };

  $scope.selectUserImage = function (imageId) {
    var element = angular.element('.input-image-folder .' + imageId);
    var isSelected = element.hasClass('selected');
    if (isSelected) {
      element.removeClass('selected');
      _.remove($scope.selectedImages, function(n) {
        return n == imageId;
      });
    } else {
      element.addClass('selected');
      $scope.selectedImages.push(imageId);
    }
  };

  $scope.selectResultImage = function (imageId) {

  };

}]);
