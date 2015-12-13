'use strict';

angular.module('medimage').service('processService', ['Restangular', function (Restangular) {

    this.binary = function (id, params, callback) {
        Restangular.one('process/binary', id).customPOST(params).then(function (data) {
            callback(data.id);
        });
    };

}]);
