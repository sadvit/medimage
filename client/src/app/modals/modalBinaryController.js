angular.module('medimage').controller('modalBinaryController', function ($scope, $uibModalInstance) {

  $scope.binaryTypes = ['MEAN', 'OTSU', 'ENTROPY', 'SQUARE', 'GAUSSIAN', 'SAUVOLA', 'THRESOLD'];

  if (!$scope.params) {
    $scope.params = {};
    $scope.params.type = 'MEAN';
  }

  $scope.selectBinaryType = function (binaryType) {
    $scope.params.type = binaryType;
  };

  $scope.apply = function () {
    $uibModalInstance.close($scope.params);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

});
