angular.module('medimage').directive('modalBinary', function () {

  var show = function () {
    angular.element('#binaryModal').modal('show');
    angular.element('.slider').slider();
    angular.element('#ex1').slider();
    angular.element('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });
  };

  var hide = function () {
    angular.element('#binaryModal').modal('hide');
  };

  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'modals/modalBinary.html',
    restrict: 'A',
    controller: function ($scope) {

      $scope.binaryTypes = ['MEAN', 'OTSU', 'ENTROPY', 'SQUARE', 'GAUSSIAN', 'SAUVOLA', 'THRESOLD'];

      $scope.selectedBinaryType = 'MEAN';

      $scope.selectBinaryType = function (binaryType) {
        $scope.selectedBinaryType = binaryType;
      };

      $scope.$watch('modalBinaryShow', function (newValue, oldValue) {
        if (newValue) {
          show();
          $scope.modalBinaryShow = false;
        }
      });
      $scope.binaryTypeSelect = function () {
        console.log('selected!');
      }
    }
  }
});
