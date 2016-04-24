'use strict';

angular.module('medimage').controller('processController', ['$scope', '$stateParams', 'modalsService', 'chainService', '$state', 'imageService', 'processService', 'tempService', function ($scope, $stateParams, modalsService, chainService, $state, imageService, processService, tempService) {

  var self = this;

  var imagesFolder = 'images';
  var tempFolder = 'temp';
  $scope.folder = imagesFolder;
  $scope.cropMode = false;

  $scope.chain = [];

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

  $scope.blurModalShow = function (blurParams) {
    if (!blurParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBlurModal(
      self.blurModalApply,
      self.blurModalCancel,
      blurParams);
  };

  $scope.binaryModalShow = function (binaryParams) {
    if (!binaryParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBinaryModal(
      self.binaryModalApply,
      self.binaryModalCancel,
      binaryParams);
  };

  $scope.histogramEqualizeModalShow = function (equalizeParams) {
    if (!equalizeParams) {
      self.chainIndex = undefined;
    }
    modalsService.showHistogramEqualizeModal(
      self.histogramEqualizeModalApply,
      self.histogramEqualizeModalCancel,
      equalizeParams);
  };

  $scope.cannyModalShow = function (cannyParams) {
    if (!cannyParams) {
      self.chainIndex = undefined;
    }
    modalsService.showCannyModal(
      self.cannyModalApply,
      self.cannyModalCancel,
      cannyParams);
  };

  $scope.changeOperation = function (index) {
    var chainElement = $scope.chain[index];
    self.chainIndex = index;
    switch (chainElement.operationType) {
      case 'BINARY':
        $scope.binaryModalShow(chainElement.binaryParams);
        break;
      case 'BLUR':
        $scope.blurModalShow(chainElement.blurParams);
        break;
      case 'HISTOGRAM_EQUALIZE':
        $scope.histogramEqualizeModalShow(chainElement.histogramEqualizeParams);
        break;
      case 'CANNY':
        $scope.cannyModalShow(chainElement.cannyParams);
        break;
    }
  };

  $scope.removeOperation = function (index) {
    $scope.chain.splice(index, 1);
  };

  self.blurModalApply = function (blurParams) {
    var chainElement = {
      operationType: 'BLUR',
      blurParams: blurParams
    };
    if (self.chainIndex != undefined) {
      $scope.chain[self.chainIndex] = chainElement;
    } else {
      $scope.chain.push(chainElement);
    }
  };

  self.blurModalCancel = function () {

  };

  self.binaryModalApply = function (binaryParams) {
    var chainElement = {
      operationType: 'BINARY',
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

  self.histogramEqualizeModalApply = function (equalizeParams) {
    var chainElement = {
      operationType: 'HISTOGRAM_EQUALIZE',
      histogramEqualizeParams: equalizeParams
    };
    if (self.chainIndex != undefined) {
      $scope.chain[self.chainIndex] = chainElement;
    } else {
      $scope.chain.push(chainElement);
    }
  };

  self.histogramEqualizeModalCancel = function () {

  };

  self.cannyModalApply = function (cannyParams) {
    var chainElement = {
      operationType: 'CANNY',
      cannyParams: cannyParams
    };
    if (self.chainIndex != undefined) {
      $scope.chain[self.chainIndex] = chainElement;
    } else {
      $scope.chain.push(chainElement);
    }
  };

  $scope.cropImage = function() {
    $scope.cropMode = true;
    document.body.style.cursor='crosshair';
    var glass = angular.element('body');
    glass.on('mousedown.crop', function (event) {
      var p1 = {
        x: event.clientX,
        y: event.clientY
      };
      glass.on('mousemove.crop', function (event) {
        var p2 = {
          x: event.clientX + 5,
          y: event.clientY - 25
        };
        $scope.drawRect(p1, p2);
        //console.log('move', p1, p2);
      });
      glass.on('mouseup.crop', function (event) {
        var p2 = {
          x: event.clientX,
          y: event.clientY
        };
        document.body.style.cursor='default';
        glass.off('mousedown.crop');
        glass.off('mouseup.crop');
        glass.off('mousemove.crop');
        $scope.$apply(function () {
          $scope.cropMode = false;
          $scope.crop(p1, p2);
        });
      });
    });
  };

  $scope.crop = function (p1, p2) {

  };

  $scope.drawRect = function (p1, p2) {
    var rect = angular.element('#rect');
    var w = p2.x - p1.x;
    var h = p2.y - p1.y;
    rect.css({
      top: p1.y + 'px',
      left: p1.x + 'px',
      width: w + 'px',
      height: h + 'px'
    })
  };

  self.cannyModalCancel = function () {

  };

  this.init = function () {
    $scope.imageId = $stateParams.imageId;
  };

  this.init();

}]);
