'use strict';

angular.module('medimage').controller('processController', ['$scope', 'processService', 'imageService', '$stateParams', 'modalsService', function ($scope, processService, imageService, $stateParams, modalsService) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;

  $scope.chain = [];

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
