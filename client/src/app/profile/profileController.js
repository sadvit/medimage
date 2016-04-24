'use strict';

angular.module('medimage').controller('profileController', ['$scope', 'userService', '$validation', '$injector', 'modalsService', function ($scope, userService, $validation, $injector, modalsService) {

  $scope.userMode = true;
  $scope.optionsMode = false;

  $scope.user = {};

  $scope.info = {};
  $scope.pass = {};
  $scope.email = {};

  $scope.newPassword = '';

  var $validationProvider = $injector.get('$validation');

  $scope.checkValid = $validationProvider.checkValid;
  $scope.reset = $validationProvider.reset;

  $scope.updateEmail = function () {
    $scope.updateUser(($scope.email));
  };

  $scope.updatePassword = function () {
    $scope.pass.newPassword = $scope.newPassword;
    $scope.updateUser($scope.pass);
  };

  $scope.updateInfo = function () {
    $scope.updateUser($scope.info);
  };

  var merge = function (objValue, srcValue) {
    return objValue ? objValue : srcValue;
  };

  $scope.updateUser = function (user) {
    var reqUser = _.assign(user, $scope.user, merge);
    userService.updateUser(reqUser, function (user) {
      modalsService.showSuccessModal(function () {
        $scope.user = user;

        $scope.email = {};

        $scope.info.address = user.address;
        $scope.info.name = user.name;
        $scope.info.surname = user.surname;

        delete $scope.newPassword;
        $scope.pass = {};

      }, {
        title: 'Updated',
        message: 'User updated successfully'
      })

    });
  };

  this.init = function () {
    userService.getUser(function (user) {
      $scope.user = user;

      $scope.info.address = user.address;
      $scope.info.name = user.name;
      $scope.info.surname = user.surname;

    });
  };

  this.init();

}]);
