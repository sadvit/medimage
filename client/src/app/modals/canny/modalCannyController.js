angular.module('medimage').controller('modalCannyController', ['$scope', '$uibModalInstance', 'cannyParams', function ($scope, $uibModalInstance, cannyParams) {

  var self = this;

  $scope.apply = function () {
    $uibModalInstance.close($scope.cannyParams);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

  self.init = function () {
    if (cannyParams) {
      $scope.isEditMode = true;
      $scope.cannyParams = cannyParams;
    } else {
      $scope.isEditMode = false;
      $scope.cannyParams = {};
    }
  };

  self.init();

}]);
