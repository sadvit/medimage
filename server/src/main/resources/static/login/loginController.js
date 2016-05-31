'use strict';

angular.module('medimage').controller('loginController', ['$scope', 'authService', '$state', function ($scope, authService, $state) {

  $scope.auth = function () {
    authService.login($scope.login, $scope.pass, function () {
      $state.go('images');
    }, function () {
      $scope.login = '';
      $scope.pass = '';
      $scope.error = true;
    });
  };

}]);
