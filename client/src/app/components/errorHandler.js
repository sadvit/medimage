angular.module('medimage').directive('errorHandler', function () {
  return {
    restrict: 'A',
    scope: {
      handler: '&errorHandler'
    },
    link: function (scope, element, attrs) {
      var handler = scope.handler();
      element.bind('error', handler);
    }
  }
});
