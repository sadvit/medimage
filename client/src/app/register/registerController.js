'use strict';

angular.module('medimage').controller('registerController', ['$scope', '$state', 'authService', function ($scope, $state, authService) {

  $scope.username = '';
  $scope.password = '';
  $scope.repeatPassword = '';
  $scope.name = '';
  $scope.surname = '';
  $scope.address = '';


  $scope.register = function () {
    if ($scope.username && $scope.password && $scope.repeatPassword) {
      authService.register({
        username: $scope.username,
        newPassword: $scope.password,
        name: $scope.name,
        surname: $scope.surname,
        address: $scope.address
      }, function () {
        authService.login($scope.username, $scope.password, function () {
          $state.go('login');
        });
      })
    }
  };

  $scope.back = function () {
    $state.go('login');
  }

}]);
