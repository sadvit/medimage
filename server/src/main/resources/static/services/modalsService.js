'use strict';

angular.module('medimage').service('modalsService', ['$uibModal', function ($uibModal) {

  this.showBinaryModal = function (apply, cancel, binaryParams) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/binary/modalBinaryTemplate.html',
      controller: 'modalBinaryController',
      resolve: {
        binaryParams: function () {
          return angular.copy(binaryParams);
        }
      }
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showBlurModal = function (apply, cancel, blurParams) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/blur/modalBlurTemplate.html',
      controller: 'modalBlurController',
      resolve: {
        blurParams: function () {
          return angular.copy(blurParams);
        }
      }
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showHistogramEqualizeModal = function (apply, cancel, equalizeParams) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/histogramEqualize/modalHistogramEqualizeTemplate.html',
      controller: 'modalHistogramEqualizeController',
      resolve: {
        equalizeParams: function () {
          return angular.copy(equalizeParams);
        }
      }
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showCannyModal = function (apply, cancel, cannyParams) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/canny/modalCannyTemplate.html',
      controller: 'modalCannyController',
      resolve: {
        cannyParams: function () {
          return angular.copy(cannyParams);
        }
      }
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showChainModal = function (apply, cancel) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/chain/modalChainTemplate.html',
      controller: 'modalChainController'
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showRecognizeModal = function (apply, cancel) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: '/modals/recognize/modalRecognizeTemplate.html',
      controller: 'modalRecognizeController'
    });
    modalInstance.result.then(apply, cancel);
  };

  this.showErrorModal = function (apply, error) {
    var modalInstance = $uibModal.open({
      animation: true,
      backdrop: 'static',
      keyboard: false,
      openedClass: 'modal-warning',
      templateUrl: '/modals/error/modalErrorTemplate.html',
      controller: 'modalErrorController',
      resolve: {
        error: function () {
          return angular.copy(error);
        }
      }
    });
    modalInstance.result.then(apply);
  };

  this.showSuccessModal = function (apply, success) {
    var modalInstance = $uibModal.open({
      animation: true,
      backdrop: 'static',
      keyboard: false,
      openedClass: 'modal-primary',
      templateUrl: '/modals/success/modalSuccessTemplate.html',
      controller: 'modalSuccessController',
      resolve: {
        success: function () {
          return angular.copy(success);
        }
      }
    });
    modalInstance.result.then(apply);
  };

}]);
