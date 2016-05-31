'use strict';

angular.module('medimage').service('authService', ['Restangular', function (Restangular) {

  this.register = function (user, callback) {
    Restangular.all('auth/register').customPOST(user).then(function () {
      callback();
    });
  };

  this.login = function (login, pass, success, error) {
    var encodedData = window.btoa(login + ':' + pass);
    Restangular.all('auth/login').customGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (resp) {
      success(resp);
    }, function (resp) {
      error(resp);
    });
  };

  this.logout = function (callback) {
    Restangular.one('auth/logout').get().then(function () {
      callback()
    });
  };

}]);
