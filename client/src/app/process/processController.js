'use strict';

angular.module('medimage').controller('processController', ['$scope', '$stateParams', 'modalsService', 'chainsService', function ($scope, $stateParams, modalsService, chainsService) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;

  $scope.chain = [];

  $scope.acceptChain = function () {
    chainsService.acceptChain($scope.imageId, $scope.chain, function (imageId) {
      $scope.folder = tempFolder;
      $scope.imageId = imageId;
    });
  };

  $scope.saveChain = function () {

  };

  $scope.binaryModalShow = function (binaryParams) {
    if (!binaryParams) self.chainIndex = undefined;
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
    }
  };

  $scope.removeOperation = function (index) {
    $scope.chain.splice(index, 1);
  };

  self.binaryModalApply = function (binaryParams) {
    var chainElement = {
      operationType: "BINARY",
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
