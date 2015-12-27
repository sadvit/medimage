angular.module('medimage').directive('statisticsMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'statistics/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope','modalsService', function ($scope, modalsService) {

    }]
  }
}]);
