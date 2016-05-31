angular.module('medimage').controller('modalHistogramEqualizeController', ['$scope', '$uibModalInstance', 'equalizeParams', function ($scope, $uibModalInstance, equalizeParams) {

  var self = this;

  $scope.apply = function () {
    $uibModalInstance.close($scope.equalizeParams);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

  self.init = function () {
    if (equalizeParams) {
      $scope.isEditMode = true;
      $scope.equalizeParams = equalizeParams;
    } else {
      $scope.isEditMode = false;
      $scope.equalizeParams = {};
      $scope.equalizeParams.isGlobal = true;
    }
  };

  self.init();

}]);
