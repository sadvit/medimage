'use strict';

angular.module('medimage').controller('processController', ['$scope', 'processService', 'imageService', 'modalService', '$stateParams', function ($scope, processService, imageService, modalService, $stateParams) {

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

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
    console.log('222 ' + $scope.imageId);
  };

  this.init();

}]);
