'use strict';

angular.module('medimage').controller('loginController', ['$scope', 'loginService', function ($scope, loginService) {

    $scope.auth = function () {
        loginService.auth($scope.login, $scope.pass, function (response) {
            console.log(response);
        });
    };

}]);
