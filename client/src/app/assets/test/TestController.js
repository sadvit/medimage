'use strict';

angular.module('medimage').controller('testController', ['$scope', 'processService', 'imageService', function ($scope, processService, imageService) {

    var index = 0;

    $scope.test = function () {
        if (index < $scope.images.length) {
            processService.binary($scope.images[index], {thresold: 30}, function (imageId) {
                    $scope.imageId = imageId;
            });
            index++;
        }
    };

    this.init = function () {
        imageService.getImages(function (images) {
            $scope.images = images;
        });
    };

    this.init();

}]);