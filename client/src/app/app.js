'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router', 'ui.bootstrap', 'ui.bootstrap-slider', 'dndLists']);

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
        'content':{
          templateUrl: 'process/process.html',
          controller: 'processController'
        }
      }
    })
    .state('images', {
      url: '/images',
      views: {
        'content':{
          templateUrl: 'images/images.html',
          controller: 'imagesController'
        }
      }
    })
    .state('chains', {
      url: '/chains',
      views: {
        'content':{
          templateUrl: 'chains/chains.html',
          controller: 'chainsController'
        }
      }
    })
    .state('statistics', {
      url: '/statistics/:imageId',
      views: {
        'content':{
          templateUrl: 'statistics/statistics.html',
          controller: 'statisticsController'
        }
      }
    })
    .state('register', {
      url: '/register'
    });

  RestangularProvider.setBaseUrl('http://localhost:8080');

});

medimage.run(['$state', '$rootScope', 'Restangular', function ($state, $rootScope, Restangular) {

  Restangular.setErrorInterceptor(function (response) {
      if (response.status == 401) {
        $state.go('login');
      }
      return false;
    }
  );

}]);
