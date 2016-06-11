angular.module('medimage').controller('modalRecognizeController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {

  $scope.getFormatDate = function () {
    return moment().format("DD-mm-YYYY");
  };

  $scope.recognizeName = 'Recognize ' + $scope.getFormatDate();

  $scope.apply = function () {
    $uibModalInstance.close($scope.recognizeName);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
