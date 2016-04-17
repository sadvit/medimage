'use strict';

angular.module('medimage').controller('recognizeController', ['$scope', 'imageService', 'networkService', 'chainsService', '$timeout', function ($scope, imageService, networkService, chainsService, $timeout) {

  var self = this;

  $scope.images = [];
  $scope.networks = [];
  $scope.chains = [];
  $scope.selectedImages = [];

  $scope.isLearnMode = false;

  $scope.isLoading = false;

  $scope.imagesToChain = [];
  $scope.imagesAfterChain = [];

  $scope.recognizeResult = [];

  $scope.types = {};

  self.init = function () {
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
    $scope.isLearnMode = false;
  };

  $scope.selectTrain = function () {
    var isOpened = angular.element('.chains-block').hasClass('show');
    if (!isOpened) {
      angular.element('.chains-block').toggleClass('show');
    }
    $scope.isLearnMode = true;
  };

  $scope.selectChain = function (event, chain) {
    var isOpened = angular.element('.input-block').hasClass('show');
    if (!isOpened) {
      angular.element('.input-block').toggleClass('show');
    }
    $scope.selectedChain = chain;
  };

  $scope.sendImagesToChain = function () {
    var isOpened = angular.element('.output-block').hasClass('show');
    if ($scope.imagesToChain.length > 0 && !isOpened) {
      angular.element('div.no-data').addClass('hide');
      angular.element('.output-block').toggleClass('show');
    }

    if ($scope.isLearnMode) {
      $scope.isLoading = true;
      chainsService.processImages($scope.selectedChain.id, $scope.imagesToChain, function (images) {
        $scope._imagesToChain = angular.copy($scope.imagesToChain);
        $scope.imagesAfterChain = images;
        $scope.isLoading = false;
      });
    } else {
      $scope.isLoading = true;
      chainsService.processImages($scope.selectedChain.id, $scope.imagesToChain, function (images) {
        networkService.recognize($scope.selectedNetwork.id, images, function (data) {
          $scope._imagesToChain = angular.copy($scope.imagesToChain);
          $scope.imagesAfterChain = images;
          $scope.isLoading = false;

          $scope.recognizeResult = data;
        });
      });
    }

  };

  /*$scope.recognizeImages = function () {
    networkService.recognize($scope.selectedNetwork.id, $scope.selectedChain.id, $scope.selectedImages, function (result) {
      // TODO stop loader
    });
    // TODO start loader
  };*/

  $scope.selectUserImage = function (imageId) {
    var element = angular.element('.input-image-folder .' + imageId);
    var isSelected = element.hasClass('selected');
    if (isSelected) {
      element.removeClass('selected');
      _.remove($scope.imagesToChain, function(n) {
        return n == imageId;
      });
    } else {
      element.addClass('selected');
      $scope.imagesToChain.push(imageId);
    }
  };

  $scope.saveResults = function () {
    if ($scope.isLearnMode) {
      var params = {};
      $scope.imagesAfterChain.forEach(function (imageId, index) {
        params[imageId] = $scope.imagesToChain[index]; //$scope.types[imageId];
      });
      networkService.learn(-1, params, function () {
        console.log('learned');
      });
    }
  };

  self.init();

}]);
