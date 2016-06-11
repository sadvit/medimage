angular.module('medimage').directive('recognizeMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'scripts/recognize/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope', 'modalsService', function ($scope, modalsService) {

    }]
  }
}]);
