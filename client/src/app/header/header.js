angular.module('medimage').directive('header', ['$state', 'loginService', function ($state, loginService) {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'header/header.html',
    restrict: 'A',
    controller: function ($scope) {
      $scope.state = $state;

      $scope.logout = function () {
        loginService.logout(function () {
          $state.go('login')
        });
      }
    }
  };
}]);
