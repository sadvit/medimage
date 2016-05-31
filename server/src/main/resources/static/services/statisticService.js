'use strict';

angular.module('medimage').service('statisticService', ['Restangular', function (Restangular) {

  this.getStatistics = function (id, callback) {
    Restangular.one('statistics', id).get().then(function (data) {
      callback(data.plain());
    });
  };

}]);
