'use strict';

angular.module('medimage').controller('statisticsController', ['$scope', '$stateParams', '$timeout', 'statisticsService', function ($scope, $stateParams, $timeout, statisticsService) {

  var self = this;

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
    statisticsService.getStatistics($scope.imageId, function (data) {
      $scope.histogramId = data.id;
    });
  };

  this.init();

}]);
