'use strict';

angular.module('medimage').service('authService', ['Restangular', function (Restangular) {

  this.register = function (user, callback) {
    Restangular.all('medimage/auth/register').customPOST(user).then(function () {
      callback();
    });
  };

  this.login = function (login, pass, success, error) {
    var encodedData = window.btoa(login + ':' + pass);
    Restangular.all('medimage/auth/login').customGET('', null, {Authorization: 'Basic ' + encodedData}).then(function (resp) {
      success(resp);
    }, function (resp) {
      error(resp);
    });
  };

  this.logout = function (callback) {
    Restangular.one('medimage/auth/logout').get().then(function () {
      callback()
    });
  };

}]);
