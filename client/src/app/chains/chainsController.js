'use strict';

angular.module('medimage').controller('chainsController', ['$scope', 'chainService', 'imageService', '$state', 'processService', 'tempService', 'modalsService', 'chainEditor', function ($scope, chainService, imageService, $state, processService, tempService, modalsService, chainEditor) {

  var self = this;

  $scope.chains = {};
  $scope.chainElements = [];
  $scope.images = [];
  $scope.selectedChain = {};
  $scope.selectedImages = [];

  $scope.resultImages = [];
  $scope.imageToSave = [];

  $scope.testArray = [];

  $scope.chainEditor = chainEditor;

  $scope.noData = {
    selectChain: true,
    applyChain: false,
    images: false
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

    chainEditor.init(element.chainElements);
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
      _.remove($scope.selectedImages, function (n) {
        return n == imageId;
      });
    } else {
      element.addClass('selected');
      $scope.selectedImages.push(imageId);
    }
  };

  $scope.saveChainChanges = function () {
    var index = _.findIndex($scope.chains, $scope.selectedChain);
    chainService.saveChain($scope.selectedChain, function (savedChain) {
      //$scope.chains[index] = savedChain;
      chainEditor.isChainChanged = false;
    });
  };

  $scope.processImages = function () {
    if ($scope.selectedImages.length > 0) {
      if (chainEditor.isChainChanged) {
        $scope.showOutputBlock();
        $scope.isResultLoading = true;
        $scope.resultImages = [];

        let chainRequest = {
          images: $scope.selectedImages,
          chain: {
            chainElements: $scope.chainElements
          }
        };

        processService.processChain(chainRequest, function (images) {
          $scope.resultImages = images;
          $scope.isResultLoading = false;
        });
      } else {
        $scope.showOutputBlock();
        $scope.isResultLoading = true;
        $scope.resultImages = [];

        let chainRequest = {
          images: $scope.selectedImages,
          chain: {
            id: $scope.selectedChain.id
          }
        };

        processService.processChain(chainRequest, function (images) {
          $scope.resultImages = images;
          $scope.isResultLoading = false;
        });
      }
    }
  };

  $scope.selectResultImage = function (imageId) {
    var element = angular.element('.output-image-folder .' + imageId);
    var isSelected = element.hasClass('selected');
    if (isSelected) {
      element.removeClass('selected');
      _.remove($scope.imageToSave, function (n) {
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
