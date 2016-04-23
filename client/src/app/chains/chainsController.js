'use strict';

angular.module('medimage').controller('chainsController', ['$scope', 'chainService', 'imageService', '$state', 'processService', 'tempService', function ($scope, chainService, imageService, $state, processService, tempService) {

  var self = this;

  $scope.chains = {};
  $scope.chainElements = [];
  $scope.images = [];
  $scope.selectedChain = {};
  $scope.selectedImages = [];

  $scope.resultImages = [];
  $scope.imageToSave = [];

  $scope.testArray = [];

  $scope.noData = {
    selectChain : true,
    applyChain : false,
    images : false
  };

  self.init = function () {
    chainService.getChains(function (chains) {
      $scope.chains = chains;
    });
    imageService.getImages(function (images) {
      $scope.images = images;
    });
  };

  self.init();

  $scope.paramsChanged = function () {
    if ($scope.isOutputShow) {
      angular.element('.output-block').toggleClass('show', false);
    }
  };

  $scope.showChainsBlock = function (event, element) {
    $scope.paramsChanged();
    if ($scope.noData) {
      $scope.noData.selectChain = false;
      $scope.noData.applyChain = true;
    }
    angular.element('.chains-block').toggleClass('show', true);
    $scope.selectedChain = element;
    $scope.chainElements = element.chainElements;
  };

  $scope.showInputBlock = function () {
    if ($scope.noData) {
      $scope.noData.applyChain = false;
      $scope.noData.images = true;
    }
    angular.element('.input-block').toggleClass('show', true);
  };

  $scope.showOutputBlock = function () {
    $scope.isOutputShow = true;
    $scope.noData = undefined;
    angular.element('.output-block').toggleClass('show', true);
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

  $scope.processImages = function () {
    if ($scope.selectedImages.length > 0) {
      $scope.showOutputBlock();
      $scope.isResultLoading = true;
      $scope.resultImages = [];

      var chainRequest = {
        images: $scope.selectedImages,
        chain: {
          id: $scope.selectedChain.id
        }
      };

      processService.processChain(chainRequest, function (images) {
        $scope.resultImages = images;;
        $scope.isResultLoading = false;
      });
    }
  };

  $scope.selectResultImage = function (imageId) {
    var element = angular.element('.output-image-folder .' + imageId);
    var isSelected = element.hasClass('selected');
    if (isSelected) {
      element.removeClass('selected');
      _.remove($scope.imageToSave, function(n) {
        return n == imageId;
      });
    } else {
      element.addClass('selected');
      $scope.imageToSave.push(imageId);
    }
  };

  $scope.saveResult = function () {
    if ($scope.imageToSave.length > 0) {
      tempService.saveTempImages($scope.imageToSave, function () {
        $state.go('images');
      })
    }
  };

}]);
