'use strict';

angular.module('medimage').controller('loginController', ['$scope', 'loginService', '$state', function ($scope, loginService, $state) {

    $scope.auth = function () {
        loginService.auth($scope.login, $scope.pass, function () {
            $state.go('images');
        }, function () {
            $scope.login = '';
            $scope.pass = '';
            $scope.error = true;
        });
    };

}]);
