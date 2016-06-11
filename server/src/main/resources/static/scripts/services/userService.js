'use strict';

angular.module('medimage').service('userService', ['Restangular', function (Restangular) {

  this.getUser = function (callback) {
    Restangular.one('medimage/users').get().then(function (user) {
      callback(user.plain());
    });
  };

  this.updateUser = function (user, callback) {
    Restangular.all('medimage/users').customPUT(user).then(function (newUser) {
      callback(newUser);
    });
  };

}]);
