'use strict';

angular.module('medimage').controller('recognizeController', ['$scope', 'imageService', 'networkService', 'chainsService', function ($scope, imageService, networkService, chainsService) {

  var self = this;

  $scope.images = [];
  $scope.networks = [];
  $scope.chains = [];
  $scope.selectedImages = [];

  self.init = function () {
    // TODO remove
    imageService.getImages(function (images) {
      $scope.images = images;
    });
    networkService.getNetworks(function (networks) {
      $scope.networks = networks;
    });
    chainsService.getChains(function (chains) {
      $scope.chains = chains;
    });
  };

  $scope.selectNetwork = function (event, network) {
    var isOpened = angular.element('.chains-block').hasClass('show');
    if (!isOpened) {
      angular.element('.chains-block').toggleClass('show');
    }
    $scope.selectedNetwork = network;
  };

  $scope.selectChain = function (event, chain) {
    var isOpened = angular.element('.input-block').hasClass('show');
    if (!isOpened) {
      angular.element('.input-block').toggleClass('show');
    }
    $scope.selectedChain = chain;
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

  self.init();

}]);
