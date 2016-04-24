angular.module('medimage').controller('modalHistogramEqualizeController', ['$scope', '$uibModalInstance', 'equalizeParams', function ($scope, $uibModalInstance, equalizeParams) {

  var self = this;

  $scope.binaryTypes = ['MEAN', 'OTSU', 'ENTROPY', 'SQUARE', 'GAUSSIAN', 'SAUVOLA', 'THRESOLD'];

  $scope.selectBinaryType = function (binaryType) {
    $scope.binaryParams.type = binaryType;
  };

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
      $scope.equalizeParams.isGlobal = false;
    }
  };

  self.init();

}]);
