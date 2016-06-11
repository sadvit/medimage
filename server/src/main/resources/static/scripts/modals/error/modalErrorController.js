angular.module('medimage').controller('modalErrorController', ['$scope', '$uibModalInstance', 'error', function ($scope, $uibModalInstance, error) {

  $scope.error = error;

  $scope.apply = function () {
    $uibModalInstance.close($scope.recognizeName);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
