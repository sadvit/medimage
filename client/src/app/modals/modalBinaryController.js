angular.module('medimage').controller('modalBinaryController', function ($scope, $uibModalInstance) {

  $scope.binaryTypes = ['MEAN', 'OTSU', 'ENTROPY', 'SQUARE', 'GAUSSIAN', 'SAUVOLA', 'THRESOLD'];
  $scope.params.type = 'MEAN';

  var self = this;

  $scope.showThresoldBlock = function () {
    $scope.params.type = 'THRESOLD';

    if (!self.slider) {
      self.slider = new Slider(".slider", {

      });
    }
    angular.element('.slider').on('change', function (event) {
        $scope.params.thresold = event.value.newValue;
    });
  };

  $scope.selectBinaryType = function (binaryType) {
    $scope.params.type = binaryType;
    switch ($scope.params.type) {
      case 'THRESOLD':
        $scope.showThresoldBlock();
            break;
    }
  };

  $scope.apply = function () {
    $uibModalInstance.close($scope.params);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

});
