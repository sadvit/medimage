'use strict';

angular.module('medimage').controller('processController', ['$scope', 'processService', 'imageService', '$stateParams', 'modalsService', function ($scope, processService, imageService, $stateParams, modalsService) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;

  $scope.chain = [];

  $scope.binaryModalShow = function (index) {
    $scope.chainIndex = index;
    $scope.params = $scope.chain[index];
    modalsService.showBinaryModal($scope, $scope.binaryModalApply, $scope.binaryModalCancel);
  };

  $scope.changeOperation = function (index) {
    var chainElement = $scope.chain[index];
    $scope.params = chainElement.binaryParams;
    switch (chainElement.operationType) {
      case 'BINARY':
        $scope.binaryModalShow();
            break;
    }
  };

  $scope.removeOperation = function (index) {
    $scope.chain.splice(index, 1);
  };

  $scope.binaryModalApply = function (params) {

    var chainElement = {
      index: $scope.chainIndex,
      operationType: "BINARY",
      binaryParams: params
    };

    $scope.chain.push(chainElement);

    /*processService.binary($stateParams.imageId, params, function (id) {
      $scope.folder = tempFolder;
      $scope.imageId = id;
      self.applyOperation('binary', params);
    });*/
  };

  $scope.binaryModalCancel = function () {
    // do nothing...
  };

  $scope.loadErrorHandler = function (error) {
    console.log('Image not found: ' + error);
  };

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
  };

  this.init();

}]);
