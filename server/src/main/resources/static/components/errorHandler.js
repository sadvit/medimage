angular.module('medimage').directive('errorHandler', ['$state', function ($state) {
  return {
    link: function (scope, element) {
      element.bind('error', function () {
        $state.go('login');
        // TODO add user auth check
        // TODO change image to fallback src
      });
    }
  };
}]);
