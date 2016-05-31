angular.module('medimage').directive('processMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: '/elements/process/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope', 'modalsService', function ($scope, modalsService) {

    }]
  }
}]);
