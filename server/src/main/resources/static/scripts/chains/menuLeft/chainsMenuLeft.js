angular.module('medimage').directive('chainsMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: '/elements/chains/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope', 'modalsService', function ($scope, modalsService) {

    }]
  }
}]);
