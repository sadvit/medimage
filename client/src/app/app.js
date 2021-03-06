'use strict';

var medimage = angular.module('medimage', ['restangular', 'ui.router', 'ui.bootstrap', 'ui.bootstrap-slider', 'dndLists', 'ngScrollbars', 'angularFileUpload', 'validation', 'validation.rule', 'ui.checkbox']);

var network_address = 'http://192.168.0.100:8080';

medimage.config(function ($stateProvider, $urlRouterProvider, RestangularProvider, ScrollBarsProvider, $validationProvider) {
  $urlRouterProvider.otherwise('login');
  $stateProvider
    .state('login', {
      url: '/login',
      views: {
        'content': {
          templateUrl: 'login/login.html',
          controller: 'loginController'
        }
      }
    })
    .state('process', {
      url: '/process/:imageId',
      views: {
        'content': {
          templateUrl: 'process/process.html',
          controller: 'processController'
        }
      }
    })
    .state('images', {
      url: '/images',
      views: {
        'content': {
          templateUrl: 'images/images.html',
          controller: 'imagesController'
        }
      }
    })
    .state('chains', {
      url: '/chains',
      views: {
        'content': {
          templateUrl: 'chains/chains.html',
          controller: 'chainsController'
        }
      }
    })
    .state('statistics', {
      url: '/statistics/:imageId',
      views: {
        'content': {
          templateUrl: 'statistics/statistics.html',
          controller: 'statisticsController'
        }
      }
    })
    .state('recognize', {
      url: '/recognize',
      views: {
        'content': {
          templateUrl: 'recognize/recognize.html',
          controller: 'recognizeController'
        }
      }
    })
    .state('profile', {
      url: '/profile',
      views: {
        'content': {
          templateUrl: 'profile/profile.html',
          controller: 'profileController'
        }
      }
    })
    .state('register', {
      url: '/register',
      views: {
        'content': {
          templateUrl: 'register/register.html',
          controller: 'registerController'
        }
      }
    });

  RestangularProvider.setBaseUrl(network_address);

  RestangularProvider.setDefaultHttpFields({
    timeout: 10000
  });

  ScrollBarsProvider.defaults = {
    scrollButtons: {
      scrollAmount: 'auto', // scroll amount when button pressed
      enable: true // enable scrolling buttons by default
    },
    axis: 'y', // enable 2 axis scrollbars by default
    autoHideScrollbar: false,
    theme: 'minimal-dark',
    advanced: {
      updateOnContentResize: true
    },
    scrollInertia: 0
  };

  $validationProvider.setValidMethod('watch');

  $validationProvider
    .setExpression({
      passwordMatch: function(value, scope) {
        return value === scope.newPassword;
      }
    })
    .setDefaultMsg({
      passwordMatch: {
        error: 'Password is not maching',
        success: 'Repeat correct'
      }
    });

  var letters = /^[a-zA-Z\s]*$/;
  $validationProvider
    .setExpression({
      letters: function(value) {
        return value && value.length > 0 && letters.test(value);
      }
    })
    .setDefaultMsg({
      letters: {
        error: 'Allowed only letters and spaces',
        success: ''
      }
    });


});

medimage.run(['$state', '$rootScope', 'Restangular', 'modalsService', function ($state, $rootScope, Restangular, modalsService) {

  var isUnauthorizedProcessed = false;

  var unauthorizedHandler = function (error) {
    if (!isUnauthorizedProcessed) {
      isUnauthorizedProcessed = true;
      modalsService.showErrorModal(function () {
        $state.go('login');
        isUnauthorizedProcessed = false;
        return false;
      }, error);
    }
  };

  var internalHandler = function (error) {
    modalsService.showErrorModal(function () {
      return false;
    }, error);
  };

  Restangular.setErrorInterceptor(function (response) {
    if (response.status == 401) {
      unauthorizedHandler(response);
    }
    if (response.status == 500 || response.status == 400) {
      internalHandler(response);
    }
  });

  $rootScope.network_address = network_address;

}]);
