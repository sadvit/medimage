'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', function ($scope, userService) {

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.oldUser = {};
  $scope.newUser = {};

  var pattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

  $scope.updateEmail = function () {
    if (!$scope.updateEmailDisabled()) {
      $scope.updateUser();
    }
  };

  $scope.updateEmailDisabled = function () {
    return !$scope.newUser.username || !pattern.test($scope.newUser.username);
  };

  $scope.updatePassword = function () {
    if (!$scope.updatePasswordDisabled()) {
      $scope.updateUser();
    }
  };

  $scope.updatePasswordDisabled = function () {
    return $scope.newUser.repeatPassword != $scope.newUser.currentPassword
      && $scope.newUser.currentPassword.length > 0
      && $scope.newUser.repeatPassword.length > 0;
  };

  $scope.updateInfo = function () {

  };

  var merge = function (objValue, srcValue) {
    return objValue ? objValue : srcValue;
  };

  $scope.updateUser = function () {
    var reqUser = _.assign($scope.newUser, $scope.oldUser, merge);
    userService.updateUser(reqUser, function (newUser) {
      $scope.oldUser = newUser;
      $scope.newUser = {};
    });
  };

  this.init = function () {
    userService.getUser(function (oldUser) {
      $scope.oldUser = oldUser;
    });
  };

  this.init();

}]);
