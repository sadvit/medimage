'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router']);

medimage.config(function ($stateProvider, $urlRouterProvider, RestangularProvider) {
  $urlRouterProvider.otherwise("login");
  $stateProvider
    .state('login', {
      url: "/login",
      templateUrl: "login/login.html",
      controller: "loginController"
    })
    .state('process', {
      url: "/process",
      templateUrl: "process/process.html",
      controller: "processController"
    })
    .state('images', {
      url: "/images",
      templateUrl: "images/images.html",
      controller: "imagesController"
    })
    .state('register', {
      url: "/register"
    });

  RestangularProvider.setBaseUrl('http://localhost:8080');
});
