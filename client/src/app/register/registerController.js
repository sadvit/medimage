'use strict';

angular.module('medimage').controller('registerController', ['$scope', '$state', 'authService', '$validation', '$injector', function ($scope, $state, authService, $validation, $injector) {

  $scope.username = '';
  $scope.newPassword = '';
  $scope.repeatPassword = '';
  $scope.name = '';
  $scope.surname = '';
  $scope.address = '';

  var $validationProvider = $injector.get('$validation');

  $scope.registerForm = {
    checkValid: $validationProvider.checkValid
  };

  $scope.register = function () {
    if ($scope.username && $scope.newPassword && $scope.repeatPassword) {
      authService.register({
        username: $scope.username,
        newPassword: $scope.newPassword,
        name: $scope.name,
        surname: $scope.surname,
        address: $scope.address
      }, function () {
        authService.login($scope.username, $scope.newPassword, function () {
          $state.go('login');
        });
      })
    }
  };

  $scope.back = function () {
    $state.go('login');
  }

}]);
