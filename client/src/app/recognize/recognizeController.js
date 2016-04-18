'use strict';

angular.module('medimage').controller('recognizeController', ['$scope', 'imageService', 'networkService', 'chainsService', 'modalsService', function ($scope, imageService, networkService, chainsService, modalsService) {

  var self = this;

  $scope.images = [];
  $scope.networks = [];
  $scope.chains = [];
  $scope.selectedImages = [];
  $scope.imagesToProcess = [];

  $scope.isLearnMode = false;
  $scope.isLoading = false;

  $scope.recognizeResult = {
    values : []
  };

  $scope.noData = {
    network : true,
    chain : false,
    images : false
  };

  $scope.types = {};

  $scope.showInputBlock = function () {
    $scope.noData.chain = false;
    $scope.noData.images = true;
    angular.element('.input-block').toggleClass('show', true);
  };

  $scope.showOutputBlock = function () {
    $scope.noData = undefined
    angular.element('.output-block').toggleClass('show', true);
  };

  $scope.showChainsBlock = function () {
    $scope.noData.network = false;
    $scope.noData.chain = true;
    angular.element('.chains-block').toggleClass('show', true);
  };

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
    $scope.showChainsBlock();
    $scope.selectedNetwork = network;
    $scope.isLearnMode = false;
  };

  $scope.selectTrain = function () {
    $scope.showChainsBlock();
    $scope.isLearnMode = true;
  };

  $scope.selectChain = function (event, chain) {
    $scope.showInputBlock();
    $scope.selectedChain = chain;
  };

  //---------------------------------------------

  $scope.createRecognizeResult = function (imageIds, tempIds) {
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
    chainsService.processImages($scope.selectedChain.id, imagesToProcess, function (imagesAfterProcess) {

      if ($scope.isLearnMode) {
        // generate recognize result
        $scope.isLoading = false;
        $scope.createRecognizeResult(imagesToProcess, imagesAfterProcess);
      } else {
        // get recognize result
        networkService.recognize($scope.selectedNetwork.id, imagesAfterProcess, function (recognizeResult) {
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
      // беру recognize object со всей информацией и отправляю на сервер
      /*var params = {};
      $scope.imagesAfterChain.forEach(function (imageId) {
        params[imageId] = $scope.types[imageId];
      });*/
      networkService.learn(-1, $scope.recognizeResult, function () {
        console.log('learned'); // TODO get network id
      });
    } else {
      modalsService.showRecognizeModal(function (name) {
        // сохраняю результаты распознования
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
