'use strict';

angular.module('medimage').service('userService', ['Restangular', function (Restangular) {

  this.getUser = function (callback) {
    Restangular.one('users').get().then(function (user) {
      callback(user.plain());
    });
  };

  this.updateUsername = function (user, callback) {
    Restangular.all('users/username').customPUT(user).then(function (newUser) {
      callback(newUser);
    });
  };

  this.updatePassword = function (user, callback) {
    Restangular.all('users/password').customPUT(user).then(function () {
      callback();
    });
  };

}]);
