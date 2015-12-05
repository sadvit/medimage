'use strict';

angular.module('medimage').service('modalService', ['Restangular', function (Restangular) {

  this.show = function (type, submit, close) {
    angular.element('#binaryModal').modal('show');
    angular.element('.slider').slider();
    angular.element('#ex1').slider();
  };

}]);
