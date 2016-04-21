angular.module('medimage').controller('modalChainController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {

  $scope.getFormatDate = function () {
    return moment().format("DD-mm-YYYY");
  };

  $scope.name = 'Chain ' + $scope.getFormatDate();

  $scope.apply = function () {
    $uibModalInstance.close($scope.name);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
