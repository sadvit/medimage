'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router']);

medimage.config(function($stateProvider, $urlRouterProvider) {
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
        });
});