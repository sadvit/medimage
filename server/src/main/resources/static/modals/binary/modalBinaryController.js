angular.module('medimage').controller('modalBinaryController', ['$scope', '$uibModalInstance', 'binaryParams', function ($scope, $uibModalInstance, binaryParams) {

  var self = this;

  $scope.binaryTypes = ['MEAN', 'OTSU', 'ENTROPY', 'SQUARE', 'GAUSSIAN', 'SAUVOLA', 'THRESOLD'];

  $scope.selectBinaryType = function (binaryType) {
    $scope.binaryParams.type = binaryType;
  };

  $scope.apply = function () {
    $uibModalInstance.close($scope.binaryParams);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

  self.init = function () {
    if (binaryParams) {
      $scope.isEditMode = true;
      $scope.binaryParams = binaryParams;
    } else {
      $scope.isEditMode = false;
      $scope.binaryParams = {};
      $scope.binaryParams.type = 'MEAN';
    }
  };

  self.init();

}]);
