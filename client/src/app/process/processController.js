'use strict';

angular.module('medimage').controller('processController', ['$scope', '$stateParams', 'modalsService', 'chainService', '$state', 'imageService', 'processService', 'tempService', 'chainEditor', function ($scope, $stateParams, modalsService, chainService, $state, imageService, processService, tempService, chainEditor) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;
  $scope.cropMode = false;

  $scope.chain = [];

  chainEditor.init($scope.chain);
  $scope.chainService = chainEditor;

  $scope.acceptChain = function () {

    var chainRequest = {
      images: [$stateParams.imageId],
      chain: {
        chainElements: $scope.chain
      }
    };

    processService.processChain(chainRequest, function (images) {
      $scope.folder = tempFolder;
      $scope.imageId = images[0];
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

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
  };

  this.init();

}]);
