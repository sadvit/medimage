angular.module('medimage').controller('modalSuccessController', ['$scope', '$uibModalInstance', 'success', function ($scope, $uibModalInstance, success) {

  $scope.success = success;

  $scope.apply = function () {
    $uibModalInstance.close($scope.recognizeName);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
