'use strict';

angular.module('medimage').controller('registerController', ['$scope', 'registerService', '$state', 'loginService', function ($scope, registerService, $state, loginService) {

  $scope.username = '';
  $scope.password = '';

  $scope.register = function () {
    registerService.register({
      username: $scope.username,
      password: $scope.password
    }, function() {
      loginService.auth($scope.username, $scope.password, function () {
        $state.go('images');
      });
    })
  }

  $scope.back = function () {
    $state.go('login');
  }

}]);
