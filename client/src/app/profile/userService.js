'use strict';

angular.module('medimage').service('userService', ['Restangular', function (Restangular) {

  this.getUser = function (callback) {
    Restangular.one('users').get().then(function (user) {
      callback(user.plain());
    });
  };

  this.updateUser = function (user, callback) {
    Restangular.all('users').customPUT(user).then(function (newUser) {
      callback(newUser);
    });
  };

}]);
