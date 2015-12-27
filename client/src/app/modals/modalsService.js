'use strict';

angular.module('medimage').service('modalsService', ['$uibModal', function ($uibModal) {

  this.showBinaryModal = function (apply, cancel, binaryParams) {
      var modalInstance = $uibModal.open({
        animation: true,
        templateUrl: '/modals/binary/modalBinaryTemplate.html',
        controller: 'modalBinaryController',
        resolve: {
          binaryParams: function() {
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
        blurParams: function() {
          return angular.copy(blurParams);
        }
      }
    });
    modalInstance.result.then(apply, cancel);
  };

}]);
