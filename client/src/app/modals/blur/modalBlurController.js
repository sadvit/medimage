angular.module('medimage').controller('modalBlurController', ['$scope', '$uibModalInstance', 'blurParams', function ($scope, $uibModalInstance, blurParams) {

  var self = this;

  $scope.blurTypes = ['GAUSSIAN', 'MEAN', 'MEDIAN'];

  $scope.selectBinaryType = function (blurType) {
    $scope.blurParams.type = blurType;
  };

  $scope.apply = function () {
    $uibModalInstance.close($scope.blurParams);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

  self.init = function () {
    if (blurParams) {
      $scope.isEditMode = true;
      $scope.blurParams = blurParams;
    } else {
      $scope.isEditMode = false;
      $scope.blurParams = {};
      $scope.blurParams.type = 'GAUSSIAN';
    }
  };

  self.init();

}]);
