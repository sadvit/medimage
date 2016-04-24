angular.module('medimage').directive('chainsMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'chains/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope', 'modalsService', function ($scope, modalsService) {

    }]
  }
}]);
