angular.module('medimage').directive('processMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'process/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: function ($scope) {
      $scope.binaryModalShow = function () {
        $scope.modalBinaryShow = true;
      };
    }
  }
}]);
