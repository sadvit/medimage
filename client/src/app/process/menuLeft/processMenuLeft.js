angular.module('medimage').directive('processMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'process/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope','modalsService', function ($scope, modalsService) {

      $scope.binaryModalShow = function () {
        modalsService.showBinaryModal($scope, $scope.binaryModalApply, $scope.binaryModalCancel)
      };

    }]
  }
}]);
