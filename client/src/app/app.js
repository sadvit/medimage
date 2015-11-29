'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router']);

medimage.config(function($stateProvider, $urlRouterProvider, RestangularProvider) {
    $urlRouterProvider.otherwise("login");
    $stateProvider
        .state('login', {
            url: "/login",
            templateUrl: "assets/login/login.html",
            controller: "loginController"
        })
        .state('test', {
            url: "/test",
            templateUrl: "assets/test/test.html",
            controller: "testController"
        })
        .state('register', {
            url: "/register"
        });

    RestangularProvider.setBaseUrl('http://localhost:8080');
});