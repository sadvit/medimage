'use strict';

angular.module('medimage').controller('processController', ['$scope', '$stateParams', 'modalsService', 'chainService', '$state', 'imageService', 'processService', 'tempService', function ($scope, $stateParams, modalsService, chainService, $state, imageService, processService, tempService) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;

  $scope.chain = [];

  $scope.acceptChain = function () {

    var chainRequest = {
      images: [$stateParams.imageId],
      chain: {
        chainElements: $scope.chain
      }
    };

    processService.processChain(chainRequest, function (images) {
      $scope.folder = tempFolder;
      $scope.imageId = images[0].id;
    });
  };

  $scope.saveChain = function () {
    modalsService.showChainModal(function (name) {
      var chain = {
        name: name,
        chainElements: $scope.chain
      };
      chainService.saveChain(chain, function () {
        $state.go('chains');
      });
    }, function () {

    });
  };

  $scope.saveImage = function () {
    var imageId = $scope.imageId;
    var query = [];
    query.push(imageId);
    tempService.saveTempImages(query, function () {
      $state.go('images');
    });
  };

  $scope.blurModalShow = function (blurParams) {
    if (!blurParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBlurModal(
      self.blurModalApply,
      self.blurModalCancel,
      blurParams);
  };

  $scope.binaryModalShow = function (binaryParams) {
    if (!binaryParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBinaryModal(
      self.binaryModalApply,
      self.binaryModalCancel,
      binaryParams);
  };

  $scope.changeOperation = function (index) {
    var chainElement = $scope.chain[index];
    self.chainIndex = index;
    switch (chainElement.operationType) {
      case 'BINARY':
        $scope.binaryModalShow(chainElement.binaryParams);
        break;
      case 'BLUR':
        $scope.blurModalShow(chainElement.blurParams);
        break;

    }
  };

  $scope.removeOperation = function (index) {
    $scope.chain.splice(index, 1);
  };

  self.blurModalApply = function (blurParams) {
    var chainElement = {
      operationType: 'BLUR',
      blurParams: blurParams
    };
    if (self.chainIndex != undefined) {
      $scope.chain[self.chainIndex] = chainElement;
    } else {
      $scope.chain.push(chainElement);
    }
  };

  self.blurModalCancel = function () {

  };

  self.binaryModalApply = function (binaryParams) {
    var chainElement = {
      operationType: 'BINARY',
      binaryParams: binaryParams
    };
    if (self.chainIndex != undefined) {
      $scope.chain[self.chainIndex] = chainElement;
    } else {
      $scope.chain.push(chainElement);
    }
  };

  self.binaryModalCancel = function () {

  };

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
  };

  this.init();

}]);
