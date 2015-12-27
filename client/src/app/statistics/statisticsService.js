'use strict';

angular.module('medimage').service('statisticsService', ['Restangular', function (Restangular) {

  this.getStatistics = function (id, callback) {
    Restangular.one('statistics', id).get().then(function (data) {
      callback(data);
    });
  };

}]);
