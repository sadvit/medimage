angular.module('medimage').directive('header', ['$state', 'authService', function ($state, authService) {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'header/header.html',
    restrict: 'A',
    controller: function ($scope) {
      $scope.state = $state;

      $scope.logout = function () {
        authService.logout(function () {
          $state.go('login')
        });
      }
    }
  };
}]);
