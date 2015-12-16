'use strict';

angular.module('medimage').controller('processController', ['$scope', 'processService', 'imageService', '$stateParams', function ($scope, processService, imageService, $stateParams) {

  //var index = 0;
/*
  var params = {
    type: 'SQUARE',
    localSquare: [
      28,
      1.0
    ]
  };*/
/*
  $scope.addOtsuFilter = function () {
    modalService.show();
  };*/
/*
  $scope.test = function () {
    if (index < $scope.images.length) {
      processService.binary($scope.images[index], params, function (imageId) {
        $scope.currentImage = imageId;
      });
      index++;
    }
  };*/

 /* $scope.openMenu = function (event) {
    angular.element(event.currentTarget).parent().toggleClass('active');
  };*/
/*
  $scope.binarySelect = function () {
    modalService.show('binary', function () {
      console.log('accept');
    });
  };*/

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';

  $scope.folder = imagesFolder;
  $scope.params = {};
  $scope.operations = [];

  this.addOperation = function (operation) {
    $scope.operations.push(operation);
  };

  this.removeOperation = function (operation) {
    // TODO remove operation by name
  };

  $scope.binaryModalApply = function (params) {

    processService.binary($stateParams.imageId, params, function (id) {
      $scope.folder = tempFolder;
      $scope.imageId = id;
      self.addOperation(params);
    });
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
