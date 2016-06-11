angular.module('medimage').directive('profileMenuLeft', [function () {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'scripts/profile/menuLeft/menuLeft.html',
    restrict: 'A',
    controller: ['$scope', 'modalsService', function ($scope, modalsService) {

    }]
  }
}]);
