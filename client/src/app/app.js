'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router', 'ui.bootstrap', 'ui.bootstrap-slider', 'dndLists', 'ngScrollbars', 'angularFileUpload']);

var network_address = 'http://192.168.0.101:8080';

medimage.config(function ($stateProvider, $urlRouterProvider, RestangularProvider, ScrollBarsProvider) {
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
    .state('recognize', {
      url: '/recognize',
      views: {
        'content':{
          templateUrl: 'recognize/recognize.html',
          controller: 'recognizeController'
        }
      }
    })
    .state('profile', {
      url: '/profile',
      views: {
        'content':{
          templateUrl: 'profile/profile.html',
          controller: 'profileController'
        }
      }
    })
    .state('register', {
      url: '/register',
      views: {
        'content':{
          templateUrl: 'register/register.html',
          controller: 'registerController'
        }
      }
    });

  RestangularProvider.setBaseUrl(network_address);

  ScrollBarsProvider.defaults = {
    scrollButtons: {
      scrollAmount: 'auto', // scroll amount when button pressed
      enable: true // enable scrolling buttons by default
    },
    axis: 'y', // enable 2 axis scrollbars by default
    autoHideScrollbar: false,
    theme: 'minimal-dark',
    advanced:{
      updateOnContentResize: true
    },
    scrollInertia: 0
  };

});

medimage.run(['$state', '$rootScope', 'Restangular', function ($state, $rootScope, Restangular) {

  Restangular.setErrorInterceptor(function (response) {
      if (response.status == 401) {
        $state.go('login');
      }
      return false;
    }
  );

  $rootScope.network_address = network_address;

}]);
