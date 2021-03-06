'use strict';

angular.module('medimage').controller('statisticsController', ['$scope', '$stateParams', '$timeout', 'statisticService', 'resultService', function ($scope, $stateParams, $timeout, statisticService, resultService) {

  var self = this;

  $scope.leftResults = [];
  $scope.rightResults = [];

  $scope.imageInfo = {};

  $scope.selectNetwork = function (network) {
    if (network && network.results) {
      $scope.selectedNetwork = network;
      $scope.isOneImage = false;
      var size = network.results.length;
      var leftSize = Math.round(size / 2);

      $scope.leftResults = [];
      $scope.rightResults = [];

      network.results.forEach(function (result, index) {
        if (index < leftSize) {
          $scope.leftResults.push(result);
        } else {
          $scope.rightResults.push(result);
        }
      });

      var rigthSize = size - leftSize;
    }
  };

  this.init = function () {
    if ($stateParams.imageId) {
      $scope.isOneImage = true;
      $scope.imageId = $stateParams.imageId;
      statisticService.getStatistics($scope.imageId, function (data) {
        $scope.imageInfo.name = data.name;
        $scope.imageInfo.format = data.format;
        $scope.imageInfo.width = data.width;
        $scope.imageInfo.height = data.height;

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
          ctx.font = '12px sans-serif';
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
        for (var key in data.histogram) {
          var value = parseInt(data.histogram[key]);
          var x = parseInt(key);
          drawLine(x * 2, realHeight, x * 2, realHeight - value * 3)
          drawLine(x * 2 - 1, realHeight, x * 2 - 1, realHeight - value * 3)
        }

        //drawNumber("0", padding - 3, height - padding / 2);

        ctx.textAlign = "center";
        var x, y;
        for (var i = 0; i <= 255; i += 15) {
          x = i * 1.72 + padding + 3;
          y = height - padding / 2 + 3;
          drawNumber(i, x, y); // horizontal
        }

        for (var i = 0; i <= 100; i += 10) {
          x = padding / 2;
          y = padding + i * 3.395 + 9;
          drawNumber(100 - i, x, y); // vertical
        }
      });
    }
    resultService.getResults(function (networks) {
      $scope.networks = networks;
      if (!$stateParams.imageId) {
        $scope.selectNetwork(networks[0])
      }
    });

  };

  this.init();

}]);
