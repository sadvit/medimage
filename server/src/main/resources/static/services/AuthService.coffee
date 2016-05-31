class AuthService

  constructor: (@Restangular) ->

  register: (user, callback) ->
    @Restangular.all('auth/register').customPOST(user).then ->
      callback()

  login: (login, pass, callback) ->
    encodedData = window.btoa(login + ':' + pass)
    @Restangular.all('auth/login').customGET('', null, Authorization: 'Basic ' + encodedData).then (resp) ->
      callback()

  logout: (callback) ->
    @Restangular.one('auth/logout').get().then ->
      callback()


AuthService.$inject = ['Restangular']
angular
  .module('medimage')
  .service('authService', AuthService)
