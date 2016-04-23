'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', function ($scope, userService) {

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.oldUser = {};
  $scope.newUser = {};

  $scope.updateUser = function () {
    userService.updateUser($scope.newUser, function (newUser) {
      $scope.oldUser = newUser;
    });
  };

  this.init = function () {
    userService.getUser(function (oldUser) {
      $scope.oldUser = oldUser;
    });
  };

  this.init();

}]);
