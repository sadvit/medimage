'use strict';

angular.module('medimage').controller('recognizeController', ['$scope', 'imageService', 'networkService', 'chainService', 'modalsService', '$state', 'processService', 'recognizeService', 'resultService', function ($scope, imageService, networkService, chainService, modalsService, $state, processService, recognizeService, resultService) {

  var self = this;

  $scope.images = [];
  $scope.networks = [];
  $scope.chains = [];
  $scope.selectedImages = [];
  $scope.imagesToProcess = [];

  $scope.isLearnMode = false;
  $scope.isLoading = false;

  $scope.isOutputShow = false;

  $scope.recognizeResult = {
    values: []
  };

  $scope.noData = {
    network: true,
    chain: false,
    images: false
  };

  $scope.types = {};

  $scope.paramsChanged = function () {
    if ($scope.isOutputShow) {
      angular.element('.output-block').toggleClass('show', false);
    }
  };

  $scope.showInputBlock = function () {
    if ($scope.noData) {
      $scope.noData.chain = false;
      $scope.noData.images = true;
    }
    angular.element('.input-block').toggleClass('show', true);
  };

  $scope.showOutputBlock = function () {
    $scope.isOutputShow = true;
    $scope.noData = undefined;
    angular.element('.output-block').toggleClass('show', true);
  };

  $scope.showChainsBlock = function () {
    if ($scope.noData) {
      $scope.noData.network = false;
      $scope.noData.chain = true;
    }
    angular.element('.chains-block').toggleClass('show', true);
  };

  self.init = function () {
    imageService.getImages(function (images) {
      $scope.images = images;
    });
    networkService.getNetworks(function (networks) {
      $scope.networks = networks;
    });
    chainService.getChains(function (chains) {
      $scope.chains = chains;
    });
  };

  $scope.selectNetwork = function (event, network) {
    $scope.paramsChanged();
    $scope.showChainsBlock();
    $scope.selectedNetwork = network;
    $scope.isLearnMode = false;
  };

  $scope.selectTrain = function () {
    $scope.paramsChanged();
    $scope.showChainsBlock();
    $scope.selectedNetwork = undefined;
    $scope.isLearnMode = true;
  };

  $scope.selectChain = function (event, chain) {
    $scope.paramsChanged();
    $scope.showInputBlock();
    $scope.selectedChain = chain;
  };

  //---------------------------------------------

  $scope.createRecognizeResult = function (imageIds, tempIds) {
    $scope.recognizeResult.values = [];
    imageIds.forEach(function (imageId, index) {
      var tempId = tempIds[index];
      $scope.recognizeResult.values.push({
        imageId: imageId,
        tempId: tempId
      })
    });
  };

  $scope.addOriginalToRecognizeResult = function (imagesToProcess, imagesAfterProcess) {
    imagesAfterProcess.forEach(function (tempId, index) {
      var value = _.find($scope.recognizeResult.values, function (n) {
        return n.tempId == tempId;
      });
      value.imageId = imagesToProcess[index];
    });
  };

  $scope.inputBlockClick = function () {
    $scope.showOutputBlock();
    var imagesToProcess = angular.copy($scope.selectedImages); // TODO remove

    $scope.isLoading = true;

    var chainRequest = {
      images: imagesToProcess,
      chain: {
        id: $scope.selectedChain.id
      }
    };

    processService.processChain(chainRequest, function (imagesAfterProcess) {

      if ($scope.isLearnMode) {
        // generate recognize result
        $scope.isLoading = false;
        $scope.createRecognizeResult(imagesToProcess, imagesAfterProcess);
      } else {
        // get recognize result
        recognizeService.recognize($scope.selectedNetwork.id, imagesAfterProcess, function (recognizeResult) {
          $scope.isLoading = false;
          $scope.recognizeResult = recognizeResult;
          $scope.addOriginalToRecognizeResult(imagesToProcess, imagesAfterProcess);
        });
      }
    });
    // after this method client have recognizeResult object with all info...
  };

  $scope.outputBlockClick = function () {
    if ($scope.isLearnMode) {
      recognizeService.learn($scope.recognizeResult, function () {
        networkService.getNetworks(function (networks) {
          $scope.networks = networks;
        });
      });
    } else {
      modalsService.showRecognizeModal(function (name) {
        $scope.recognizeResult.name = name;
        resultService.saveResults($scope.selectedNetwork.id, $scope.recognizeResult, function () {
          $state.go('statistics');
        })
      }, function () {
        console.log(false)
      })
    }
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

  self.init();

}]);
