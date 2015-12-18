'use strict';

angular.module('medimage').service('modalsService', ['$uibModal', function ($uibModal) {

  this.showBinaryModal = function ($scope, apply, cancel) {
      var modalInstance = $uibModal.open({
        animation: false,
        templateUrl: '/modals/modalBinaryTemplate.html',
        controller: 'modalBinaryController',
        //size: 'sm',
        scope: $scope
      });
      modalInstance.result.then(apply, cancel);
  };

}]);
