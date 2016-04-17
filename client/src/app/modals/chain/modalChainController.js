angular.module('medimage').controller('modalChainController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {

  $scope.apply = function () {
    $uibModalInstance.close($scope.name);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
