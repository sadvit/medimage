'use strict';

angular.module('medimage').service('modalService', ['Restangular', function (Restangular) {

  this.show = function (type, submit, close) {
    angular.element('#binaryModal').modal('show');
    angular.element('.slider').slider();
    angular.element('#ex1').slider();
    //iCheck for checkbox and radio inputs
    angular.element('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });
  };

}]);
