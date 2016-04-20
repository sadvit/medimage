'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', function ($scope, userService) {

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.user = {};
  $scope.newUser = {};

  $scope.updateUsername = function () {
    userService.updateUsername($scope.newUser, function (user) {
      $scope.user.username = user.username;
    });
  };

  $scope.updatePassword = function () {
    userService.updatePassword($scope.newUser, function () {
      console.log('ok')
    });
  };

  this.init = function () {
    userService.getUser(function (user) {
      $scope.user = user;
    });
  };

  this.init();

}]);
