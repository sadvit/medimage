'use strict';

angular.module('medimage').service('registerService', ['Restangular', function (Restangular) {

  this.register = function (user, callback) {
    Restangular.all('users/register').customPOST(user).then(function () {
      callback();
    });
  }

}]);
