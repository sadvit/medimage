angular.module('medimage').directive('header', ['$state', function ($state) {
  return {
    link: function (scope, element, attrs) {

    },
    templateUrl: 'header/header.html',
    restrict: 'A',
    controller: function ($scope) {
      $scope.state = $state;
    }
  };
}]);
