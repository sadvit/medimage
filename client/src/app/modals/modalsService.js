'use strict';

angular.module('medimage').service('modalsService', ['$uibModal', function ($uibModal) {

  this.showBinaryModal = function (apply, cancel, binaryParams) {
      var modalInstance = $uibModal.open({
        animation: false,
        templateUrl: '/modals/modalBinaryTemplate.html',
        controller: 'modalBinaryController',
        resolve: {
          binaryParams: function() {
            return angular.copy(binaryParams);
          }
        }
      });
      modalInstance.result.then(apply, cancel);
  };

}]);
