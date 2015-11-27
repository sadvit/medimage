angular.module('medimage').controller('testController', ['$scope', 'processService', 'imageService', function ($scope, processService, imageService) {

    var index = 0;

    $scope.imageId = 'flower';

    $scope.test = function () {
        if (index < $scope.images.length) {
            index++;
            processService.binary($scope.images[index], {thresold: 30}, function (imageId) {
                //$scope.$apply(function () {
                    $scope.imageId = imageId;
                //});
            })
        }
    };

    this.init = function () {
        imageService.getImages(function (images) {
            $scope.images = images;
        });
    };

    this.init();

}]);