'use strict';

angular.module('medimage').service('registerService', ['Restangular', function (Restangular) {

  this.register = function (user, callback) {
    Restangular.all('auth/register').customPOST(user).then(function () {
      callback();
    });
  }

}]);
