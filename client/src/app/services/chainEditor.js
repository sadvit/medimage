'use strict';

angular.module('medimage').service('chainEditor', ['modalsService', function (modalsService) {

  var self = this;

  self.isChainChanged = false;

  self.setChain = function (chainElements) {
    self.chainElements = chainElements;
  };

  self.getChain = function () {
    return self.chainElements;
  };

  self.blurModalShow = function (blurParams) {
    if (!blurParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBlurModal(
      self.blurModalApply,
      self.blurModalCancel,
      blurParams);
  };

  self.binaryModalShow = function (binaryParams) {
    if (!binaryParams) {
      self.chainIndex = undefined;
    }
    modalsService.showBinaryModal(
      self.binaryModalApply,
      self.binaryModalCancel,
      binaryParams);
  };

  self.histogramEqualizeModalShow = function (equalizeParams) {
    if (!equalizeParams) {
      self.chainIndex = undefined;
    }
    modalsService.showHistogramEqualizeModal(
      self.histogramEqualizeModalApply,
      self.histogramEqualizeModalCancel,
      equalizeParams);
  };

  self.cannyModalShow = function (cannyParams) {
    if (!cannyParams) {
      self.chainIndex = undefined;
    }
    modalsService.showCannyModal(
      self.cannyModalApply,
      self.cannyModalCancel,
      cannyParams);
  };

  self.blurModalApply = function (blurParams) {
    var chainElement = {
      operationType: 'BLUR',
      blurParams: blurParams
    };
    if (self.chainIndex != undefined) {
      self.chainElements[self.chainIndex] = chainElement;
    } else {
      self.chainElements.push(chainElement);
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
      self.chainElements[self.chainIndex] = chainElement;
    } else {
      self.chainElements.push(chainElement);
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
      self.chainElements[self.chainIndex] = chainElement;
    } else {
      self.chainElements.push(chainElement);
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
      self.chainElements[self.chainIndex] = chainElement;
    } else {
      self.chainElements.push(chainElement);
    }
  };

  self.changeOperation = function (index) {
    var chainElement = self.chainElements[index];
    self.chainIndex = index;
    switch (chainElement.operationType) {
      case 'BINARY':
        self.binaryModalShow(chainElement.binaryParams);
        break;
      case 'BLUR':
        self.blurModalShow(chainElement.blurParams);
        break;
      case 'HISTOGRAM_EQUALIZE':
        self.histogramEqualizeModalShow(chainElement.histogramEqualizeParams);
        break;
      case 'CANNY':
        self.cannyModalShow(chainElement.cannyParams);
        break;
    }
  };

  self.removeOperation = function (index) {
    self.chainElements.splice(index, 1);
  };

}]);
