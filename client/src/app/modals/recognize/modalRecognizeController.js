angular.module('medimage').controller('modalRecognizeController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {

  $scope.getFormatDate = function () {
    var d = new Date();
    var day = d.getUTCDate();
    var month = d.getUTCMonth();
    var year = d.getUTCFullYear();
    var minutes = d.getUTCMinutes();
    var hours = d.getUTCHours();
    return day + '.' + month + '.' + year + ' - ' + hours + ':' + minutes;
  };

  $scope.networkName = 'Network ' + $scope.getFormatDate();
  $scope.recognizeName = 'Recognize ' + $scope.getFormatDate();

  $scope.apply = function () {
    $uibModalInstance.close($scope.name);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };

}]);
