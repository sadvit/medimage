'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router']);

medimage.config(function ($stateProvider, $urlRouterProvider, RestangularProvider) {
  $urlRouterProvider.otherwise('login');
  $stateProvider
    .state('login', {
      url: '/login',
      views: {
        'content':{
          templateUrl: 'login/login.html',
          controller: 'loginController'
        }
      }
    })
    .state('process', {
      url: '/process/:imageId',
      views: {
        'header':{
          templateUrl: 'header/header.html',
          controller: 'headerController'
        },
        'content':{
          templateUrl: 'process/process.html',
          controller: 'processController'
        },
        'leftBar':{
          templateUrl: 'processLeftBar/processLeftBar.html',
          controller: 'processLeftBarController'
        },
        'rightBar':{
          templateUrl: 'processRightBar/processRightBar.html',
          controller: 'processRightBarController'
        }
      }
    })
    .state('images', {
      url: '/images',
      views: {
        'header':{
          templateUrl: 'header/header.html',
          controller: 'headerController'
        },
        'content':{
          templateUrl: 'images/images.html',
          controller: 'imagesController'
        },
        'leftBar':{
          templateUrl: 'imagesLeftBar/imagesLeftBar.html',
          controller: 'imagesLeftBarController'
        }
      }
    })
    .state('register', {
      url: '/register'
    });

  RestangularProvider.setBaseUrl('http://localhost:8080');

});
