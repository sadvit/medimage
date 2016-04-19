'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', function ($scope, userService) {

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.user = {};
  $scope._user = {};

  $scope.updateUsername = function () {
    userService.updateUsername($scope._user, function () {
      console.log('ok')
    });
  };

  $scope.updatePassword = function () {
    userService.updatePassword($scope._user, function () {
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
