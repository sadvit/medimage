'use strict';

angular.module('medimage').controller('registerController', ['$scope', 'registerService', '$state', 'authService', function ($scope, registerService, $state, authService) {

  $scope.username = '';
  $scope.password = '';
  $scope.repeatPassword = '';
  $scope.name = '';
  $scope.surname = '';
  $scope.address = '';


  $scope.register = function () {
    if ($scope.username && $scope.password && $scope.repeatPassword) {
      registerService.register({
        username: $scope.username,
        password: $scope.password,
        name: $scope.name,
        surname: $scope.surname,
        address: $scope.address
      }, function () {
        authService.auth($scope.username, $scope.password, function () {
          $state.go('login');
        });
      })
    }
  };

  $scope.back = function () {
    $state.go('login');
  }

}]);
