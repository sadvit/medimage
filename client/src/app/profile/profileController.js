'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', function ($scope, userService) {

  var pattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

  var isEmpty = function (str) {
    return !str || str.length == 0;
  };

  var isEmail = function(email) {
    if (!isEmpty(email)) {
      return pattern.test(email);
    }
  };

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.oldUser = {};
  $scope.newUser = {};

  $scope.emailFocused = false;

  $scope.info = {};
  $scope.pass = {};
  $scope.email = {};

  $scope.updateEmail = function () {
    if (!$scope.updateEmailDisabled()) {
      $scope.updateUser(($scope.email));
    }
  };

  $scope.updatePassword = function () {
    if (!$scope.updatePasswordDisabled()) {
      $scope.updateUser($scope.pass);
    }
  };

  $scope.updateInfo = function () {
    if (!$scope.updateInfoDisabled()) {
      $scope.updateUser($scope.info);
    }
  };

  $scope.updateEmailDisabled = function () {
    return $scope.emailFocused && !isEmail($scope.email.username);
  };

  $scope.updatePasswordDisabled = function () {
    return $scope.passFocused && ($scope.pass.repeatPassword != $scope.pass.newPassword
      || isEmpty($scope.pass.currentPassword)
      || isEmpty($scope.pass.repeatPassword)
      || isEmpty($scope.pass.newPassword));
  };

  $scope.updateInfoDisabled = function () {
    return $scope.infoFocused && (isEmpty($scope.info.address)
      || isEmpty($scope.info.name)
      || isEmpty($scope.info.surname));
  };

  $scope.emailFocus = function () {
    $scope.emailFocused = true;
  };

  $scope.passFocus = function () {
    $scope.passFocused = true;
  };

  $scope.infoFocus = function () {
    $scope.infoFocused = true;
  };

  var merge = function (objValue, srcValue) {
    return objValue ? objValue : srcValue;
  };

  $scope.updateUser = function (user) {
    var reqUser = _.assign(user, $scope.oldUser, merge);
    userService.updateUser(reqUser, function (newUser) {
      $scope.email = {};
      $scope.oldUser = newUser;
      $scope.newUser = {};
    });
  };

  this.init = function () {
    userService.getUser(function (oldUser) {
      $scope.oldUser = oldUser;

      $scope.info.address = oldUser.address;
      $scope.info.name = oldUser.name;
      $scope.info.surname = oldUser.surname;


    });
  };

  this.init();

}]);
