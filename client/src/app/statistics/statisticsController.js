'use strict';

angular.module('medimage').controller('statisticsController', ['$scope', '$stateParams', '$timeout', 'statisticsService', function ($scope, $stateParams, $timeout, statisticsService) {

  var self = this;

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
    statisticsService.getStatistics($scope.imageId, function (data) {
      $scope.histogramId = data.id;
      var canvas = angular.element('#histogram');
      var ctx = canvas.get(0).getContext("2d");
      ctx.translate(0.5, 0.5);
      ctx.lineWidth = 1;

      var drawLine = function (x1, y1, x2, y2) {
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2);
        ctx.stroke();
      };

      var drawNumber = function (value, x, y) {
        ctx.fillStyle = "#222D32";
        ctx.font = 'bold 12px Calibri';
        ctx.fillText(value, x, y);
        ctx.fill();
      };

      var padding = 25, width = 500, height = 400;
      ctx.strokeStyle = '#222D32';
      ctx.beginPath();
      ctx.moveTo(padding, padding);
      ctx.lineTo(padding, height - padding);
      ctx.lineTo(width - padding, height - padding);
      ctx.stroke();
      ctx.strokeStyle = '#3c8dbc';
      var realHeight = height - padding;
      for (var key in data) {
        var value = parseInt(data[key]);
        var x = parseInt(key);
        drawLine(x * 2, realHeight, x * 2, realHeight - value * 3)
        drawLine(x * 2 - 1, realHeight, x * 2 - 1, realHeight - value * 3)
      }

      //drawNumber("0", padding - 3, height - padding / 2);

      for (var i = 0; i <= 255; i += 15) {
        drawNumber(i + "", i * 1.71 + padding, height - padding / 2 + 3);
      }
      ctx.textAlign="center";
      for (var i = 0; i <= 100; i += 10) {
        drawNumber(100 - i, padding / 2, padding + i * 3.4 + 8);
      }
    });
  };

  this.init();

}]);
