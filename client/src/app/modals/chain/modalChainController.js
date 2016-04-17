angular.module('medimage').controller('modalChainController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {

  $scope.getFormatDate = function () {
    var d = new Date();
    var day = d.getUTCDate();
    var month = d.getMonth();
    var year = d.getFullYear();
    return day + '.' + month + '.' + year;
  };

  $scope.name = $scope.getFormatDate();

  $scope.apply = function () {
    $uibModalInstance.close($scope.name);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
