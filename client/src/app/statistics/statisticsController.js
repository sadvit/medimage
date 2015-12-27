'use strict';

angular.module('medimage').controller('statisticsController', ['$scope', '$stateParams', '$timeout', 'statisticsService', function ($scope, $stateParams, $timeout, statisticsService) {

  var self = this;

  self.getArray = function () {
    var res = [];
    for(var i = 1; i < 33; i++) {
      res.push(i);
    }
    return res;
  };

  $scope.labels = self.getArray();

  $scope.data = [];

  $scope.onClick = function (points, evt) {
    console.log(points, evt);
  };

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
    statisticsService.getStatistics($scope.imageId, function (data) {
      $scope.data.push(data);
    });
  };

  this.init();

}]);
